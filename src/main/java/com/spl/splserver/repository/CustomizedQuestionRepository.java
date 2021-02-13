package com.spl.splserver.repository;

import com.spl.splserver.POJO.LearnState;

public interface CustomizedQuestionRepository {
    void updateLearnState(String questionId, LearnState state);
}
