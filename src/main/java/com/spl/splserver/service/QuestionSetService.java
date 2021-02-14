package com.spl.splserver.service;

import com.spl.splserver.entity.QuestionSet;

import java.util.List;

/*
    QuestionSetService

    All available operations to modify question sets
 */
public interface QuestionSetService {
    QuestionSet getQuestionSet(String setId);
    List<QuestionSet> getAllQuestionSetsByUser(String ownerId);
}
