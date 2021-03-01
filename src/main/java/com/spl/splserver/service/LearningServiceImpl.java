package com.spl.splserver.service;

import com.spl.splserver.POJO.LearnState;
import com.spl.splserver.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

/*
    LearningServiceImpl

 Method     setRepeatDate
            @param LearnState
            @return boolean
                if learn state is updated return ture else false
            Procedure:
             1. score == 100
                check first time review
                        |------------------------
                        | true                   | false
                        |                        |
                 repeat on next day          check interval between last review and current review > repeat interval
                 set repeat interval 1                                  |--------------------------------
                        |                                               | true                           |false
                        |                                               |                                |
                        |                       set a new repeat interval and a date           update review time only
                        |                                               |                                |
                        |                                               |                                |
                        |  <-----------------------------------------------------------------------------
                     return true
            2. score < 100
                check first time review
                        |------------------------
                        | true                   | false
                        |                        |
                 repeat after 2 hours         check interval between last review and current review > 2 hours
                        |                                               |--------------------------------
                        |                                               | true                           |false
                        |                                               |                                |
                        |                                       set a 2 hour repeat          half of original time (min 30 mins)
                        |                                               |                                |
                        |                                               |                                |
                        |  <-----------------------------------------------------------------------------
                     return true

 Method     setRepeatChance TODO : limit chance in range (0,1]
            @param LearnState preState
            @param LearnState curState
            @return boolean
                if learn state is updated return ture else false
            Procedure:
                check if it is same day repeat
                    |--------------------------------------------------------------------
                    |true                                                                | false
                    |                                                                    |
                chance = 1                                                  preRepeatChance  weight 0.5
                    |                                                       score difference weight real reviewed interval/scheduled reviewed interval
                    |                                                                    |
                    |                                                                    |
                    |<-------------------------------------------------------------------
                reuturn true
 */
@Service
public class LearningServiceImpl implements LearningService {
    @Autowired
    final QuestionRepository questionRepository;
    final QuestionService questionService;

    public LearningServiceImpl(QuestionRepository questionRepository, QuestionService questionService) {
        this.questionRepository = questionRepository;
        this.questionService = questionService;
    }

    @Override
    public void updateLearnState(String questionId, LearnState learnState) {
        LearnState preState = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream obs = new ObjectOutputStream(out);
            obs.writeObject(learnState);
            obs.close();

            ByteArrayInputStream ios = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(ios);
            preState = (LearnState) ois.readObject();
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

        boolean dataUpdated = this.setRepeatDate(learnState);
        boolean chanceUpdated = this.setRepeatChance(preState, learnState);
        if(dataUpdated && chanceUpdated)
            questionRepository.updateLearnState(questionId, learnState);
    }

    @Override
    public boolean setRepeatDate(LearnState learnState) {
        if (learnState == null)
            return false;
        Date reviewedAt = new Date();
        Instant reviewDate = reviewedAt.toInstant();
        Instant nextRepeatDate = null;
        Long lastInterval = learnState.getRepeatedInterval();

        if (learnState.getScore() == 100) {
            if (lastInterval == null) {
                learnState.setRepeatedInterval((long) 1);
                nextRepeatDate = reviewDate.plus(learnState.getRepeatedInterval(), ChronoUnit.DAYS);
            } else {
                long currentInterval = ChronoUnit.DAYS.between(learnState.getReviewedAt().toInstant(), learnState.getRepeatAt().toInstant());
                long daysAfterLastReview = ChronoUnit.DAYS.between(learnState.getReviewedAt().toInstant(), reviewDate);

                if (daysAfterLastReview >= lastInterval) {
                    learnState.setRepeatedInterval(currentInterval);
                    nextRepeatDate = reviewDate.plus(currentInterval + lastInterval, ChronoUnit.DAYS);
                } else {
                    nextRepeatDate = learnState.getRepeatAt().toInstant();
                }

            }
        } else {
            Date lastReviewedAt = learnState.getReviewedAt();
            if (lastReviewedAt == null) {
                nextRepeatDate = reviewDate.plus(2, ChronoUnit.HOURS);
            } else {
                long pastTime = ChronoUnit.MINUTES.between(lastReviewedAt.toInstant(), reviewedAt.toInstant());
                if(pastTime >= 120) {
                    nextRepeatDate = reviewDate.plus(2, ChronoUnit.HOURS);
                } else {
                    if(pastTime/2 >= 30) {
                        nextRepeatDate = reviewDate.plus(pastTime/2, ChronoUnit.MINUTES);
                    } else {
                        nextRepeatDate = reviewDate.plus(30, ChronoUnit.MINUTES);
                    }
                }
            }

        }

        if(nextRepeatDate == null)
            return false;

        learnState.setReviewedAt(reviewedAt);
        learnState.setRepeatAt(Date.from(nextRepeatDate));

        return true;
    }

    @Override
    public boolean setRepeatChance(LearnState preState, LearnState curState) {
        Date nextRepeatAt = curState.getRepeatAt();
        Float repeatChance = Float.MAX_VALUE;
        if (ChronoUnit.DAYS.between(new Date().toInstant(), nextRepeatAt.toInstant()) <= 0) {
            repeatChance = 1.0F;
        } else {
            Float preRepeatChance = preState.getRepeatChance();
            Integer curScore = curState.getScore();
            Integer preScore = Optional.ofNullable(curState.getLastScore()).orElse(0);

            long scheduledInterval = ChronoUnit.SECONDS.between(preState.getReviewedAt().toInstant(),preState.getRepeatAt().toInstant());
            long realInterval = ChronoUnit.SECONDS.between(preState.getReviewedAt().toInstant(),curState.getReviewedAt().toInstant());
            double intervalPercentage = Math.tan((double) realInterval/scheduledInterval);

            repeatChance = preRepeatChance * 0.5F + (float)intervalPercentage * (float)Math.tanh((double)(preScore - curScore)/100);

        }
        if(repeatChance == Float.MAX_VALUE)
            return false;
        curState.setRepeatChance(repeatChance);

        return true;
    }

}
