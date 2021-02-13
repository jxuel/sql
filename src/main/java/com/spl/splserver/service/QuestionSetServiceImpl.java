package com.spl.splserver.service;

import com.spl.splserver.entity.QuestionSet;
import com.spl.splserver.repository.QuestionSetRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionSetServiceImpl implements QuestionSetService{
    @Autowired
    final QuestionSetRepository questionSetRepository;

    public QuestionSetServiceImpl(QuestionSetRepository questionSetRepository) {
        this.questionSetRepository = questionSetRepository;
    }

    @Override
    public QuestionSet getQuestionSet(String setId) {
        return questionSetRepository.findById(new ObjectId(setId)).orElse(null);
    }

    @Override
    public QuestionSet getAllQuestionSetsByUser(String ownerId) {
        return null;
    }
}
