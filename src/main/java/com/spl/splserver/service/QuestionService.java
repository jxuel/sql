package com.spl.splserver.service;

import com.spl.splserver.POJO.LearnState;
import com.spl.splserver.POJO.QuestionPriority;
import com.spl.splserver.entity.Question;

import java.util.List;
/*
    QuestionService

    All available operations to modify questions
 */
public interface QuestionService {
    LearnState checkAnswer(String questionId, List<String> answers);
    Question getQuestionById(String questionId);
    Question createQuestion(Question question);
    List<Question> getAllQuestionsBySet(String setId);
    Integer updateQuestionAnswers(String questionId, List<String> updatedAnswers);
    Integer updateQuestionOptions(String questionId, List<String> updatedOptions);
    Question updatePriority(String questionId, QuestionPriority updatedPriority);

}
