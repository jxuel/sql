package com.spl.splserver.service;

import com.spl.splserver.POJO.LearnState;
import com.spl.splserver.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        if (lastInterval == null) {
            learnState.setRepeatedInterval((long) (1000 * 60 * 60 * 24));
            learnState.setReviewedAt(new Date());
            learnState.setRepeatAt(new Date(learnState.getReviewedAt().getTime() + (1000 * 60 * 60 * 24)));
        } else {
            learnState.setRepeatedInterval( learnState.getRepeatAt().getTime() - learnState.getReviewedAt().getTime());
            Date updateRepeat = new Date();
            updateRepeat = new Date(updateRepeat.getTime() + learnState.getRepeatedInterval() + lastInterval);
            learnState.setReviewedAt(new Date());
            learnState.setRepeatAt(updateRepeat);
        }

        questionRepository.updateLearnState(questionId, learnState);

        return true;
    }
}
