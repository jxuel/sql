package com.spl.splserver.service;

import com.spl.splserver.POJO.LearnState;
import com.spl.splserver.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
/*
    LearningServiceImpl

        setRepeatDate
            @param LearnState
            @return boolean
                if learn state is updated return ture else false
            Procedure:
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
 */
@Service
public class LearningServiceImpl implements LearningService {
    @Autowired
    final QuestionRepository questionRepository;

    public LearningServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public boolean setRepeatDate(String questionId, LearnState learnState) {
        if (learnState == null)
            return false;
        Long lastInterval = learnState.getRepeatedInterval();
        Date reviewedAt = new Date();
        Instant reviewDate = reviewedAt.toInstant();
        Instant nextRepeatDate;
        if (lastInterval == null) {
            learnState.setRepeatedInterval((long)1);
            nextRepeatDate = reviewDate.plus(learnState.getRepeatedInterval(), ChronoUnit.DAYS);
        } else {
            long currentInterval = ChronoUnit.DAYS.between(learnState.getReviewedAt().toInstant(), learnState.getRepeatAt().toInstant());
            long daysAfterLastReview = ChronoUnit.DAYS.between(learnState.getReviewedAt().toInstant(), reviewDate);

            if(daysAfterLastReview >= lastInterval) {
                learnState.setRepeatedInterval(currentInterval);
                nextRepeatDate = reviewDate.plus(currentInterval+lastInterval, ChronoUnit.DAYS);
            } else {
                nextRepeatDate = learnState.getRepeatAt().toInstant();
            }

        }

        learnState.setReviewedAt(reviewedAt);
        learnState.setRepeatAt(Date.from(nextRepeatDate));

        questionRepository.updateLearnState(questionId, learnState);

        return true;
    }
}
