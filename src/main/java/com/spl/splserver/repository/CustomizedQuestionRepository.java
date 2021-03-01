package com.spl.splserver.repository;

import com.spl.splserver.POJO.LearnState;
import com.spl.splserver.entity.Question;

import java.util.List;

public interface CustomizedQuestionRepository {
    void updateLearnState(String questionId, LearnState state);
    List<Question> findByChanceAndSetId(String questionSetId, Integer quantity);
}
