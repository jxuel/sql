package com.spl.splserver.service;

import com.spl.splserver.entity.Question;
import com.spl.splserver.entity.QuestionSet;

import java.util.List;

/*
    QuestionSetService

    All available operations to modify question sets
 */
public interface QuestionSetService {
    QuestionSet createSingleQuestionSet(QuestionSet questionSet);
    QuestionSet getQuestionSet(String setId);
    boolean deleteQuestionSet(String setId);
    List<QuestionSet> getAllQuestionSetsByUser(String ownerId);
    List<Question>getTestSet(String id, Integer quantity);
}
