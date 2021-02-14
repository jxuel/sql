package com.spl.splserver.service;

import com.spl.splserver.entity.QuestionSet;
import com.spl.splserver.repository.QuestionSetRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionSetServiceImpl implements QuestionSetService{
    @Autowired
    final QuestionSetRepository questionSetRepository;

    public QuestionSetServiceImpl(QuestionSetRepository questionSetRepository) {
        this.questionSetRepository = questionSetRepository;
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
