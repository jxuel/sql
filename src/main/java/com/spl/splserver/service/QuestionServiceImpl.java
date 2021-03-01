package com.spl.splserver.service;

import com.spl.splserver.POJO.LearnState;
import com.spl.splserver.POJO.QuestionPriority;
import com.spl.splserver.entity.Question;
import com.spl.splserver.repository.QuestionRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/*
    QuestionServiceImpl

    The implementation of QuestionService
 */

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public LearnState checkAnswer(String questionId, List<String> answers) {
        Question question = getQuestionById(questionId);
        if(question != null) {

            LearnState learnState = new LearnState();
            ArrayList<String> realAnswers = question.getAnswers();

            learnState.setSubmittedAnswer((ArrayList<String>) answers);
            learnState.setRepeatAt(question.getRepeatAt());
            learnState.setLastAnswer(question.getLastAnswer());
            learnState.setLastScore(question.getLastScore());
            learnState.setRepeatChance(question.getRepeatChance());
            learnState.setReviewedAt(question.getReviewedAt());
            learnState.setRepeatedInterval(question.getRepeatInterval());
            learnState.setAnswers(question.getAnswers());

            int score = Math.round((answers.stream().filter(realAnswers::contains).count()/(float)realAnswers.size()) * 100);
            learnState.setScore(score);
            return learnState;

        }
        return null;
    }

    @Override
    public Question createQuestion(Question question) {
        question.setRepeatChance(0.5F);
        return questionRepository.save(question);
    }

    @Override
    public Question getQuestionById(String questionId) {
        Optional<Question> question = questionRepository.findById(new ObjectId(questionId));
        return question.orElse(null);
    }

    @Override
    public boolean deleteQuestionSet(String questionId) {
        questionRepository.deleteById(new ObjectId(questionId));
        return questionRepository.findById(new ObjectId(questionId)).isEmpty();
    }

    @Override
    public List<Question> getAllQuestionsBySet(String setId) {
        return null;
    }

    @Override
    public Integer updateQuestionAnswers(String questionId, List<String> updatedAnswers) {
        return null;
    }

    @Override
    public Integer updateQuestionOptions(String questionId, List<String> updatedOptions) {
        return null;
    }

    @Override
    public Question updatePriority(String questionId, QuestionPriority updatedPriority) {
        return null;
    }
}
