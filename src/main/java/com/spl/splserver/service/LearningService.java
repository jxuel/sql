package com.spl.splserver.service;

import com.spl.splserver.POJO.LearnState;
/*
    LearningService

    All available operations to update learning state
 */
public interface LearningService {
    boolean setRepeatChance(LearnState preState, LearnState curState);
    boolean setRepeatDate(LearnState learnState);

    void updateLearnState(String questionId, LearnState learnState);
}
