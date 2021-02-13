package com.spl.splserver.service;

import com.spl.splserver.entity.QuestionSet;
/*
    QuestionSetService

    All available operations to modify question sets
 */
public interface QuestionSetService {
    QuestionSet getQuestionSet(String setId);
    QuestionSet getAllQuestionSetsByUser(String ownerId);
}
