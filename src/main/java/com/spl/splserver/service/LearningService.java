package com.spl.splserver.service;

import com.spl.splserver.POJO.LearnState;
/*
    LearningService

    All available operations to update learning state
 */
public interface LearningService {
    boolean setRepeatDate(String questionId, LearnState learnState);
}
