package com.spl.splserver.service;

import com.spl.splserver.entity.Question;
import com.spl.splserver.entity.QuestionSet;
import com.spl.splserver.repository.QuestionRepository;
import com.spl.splserver.repository.QuestionSetRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
    QuestionSetServiceImpl

    The implementation of QuestionSetService

 */

@Service
public class QuestionSetServiceImpl implements QuestionSetService{
    @Autowired
    final QuestionSetRepository questionSetRepository;
    final QuestionRepository questionRepository;

    public QuestionSetServiceImpl(QuestionSetRepository questionSetRepository, QuestionRepository questionRepository) {
        this.questionSetRepository = questionSetRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getTestSet(String id, Integer quantity) {
        return questionRepository.findByChanceAndSetId(id, quantity);
    }

    @Override
    public QuestionSet createSingleQuestionSet(QuestionSet questionSet) {
        return questionSetRepository.save(questionSet);
    }

    @Override
    public QuestionSet getQuestionSet(String setId) {
        return questionSetRepository.findById(new ObjectId(setId)).orElse(null);
    }

    @Override
    public List<QuestionSet> getAllQuestionSetsByUser(String ownerId) {
        return questionSetRepository.findByOwnerId(ownerId);
    }

    @Override
    public boolean deleteQuestionSet(String setId) {
        questionSetRepository.deleteById(new ObjectId(setId));
        return questionSetRepository.findById(new ObjectId(setId)).isEmpty();
    }
}
