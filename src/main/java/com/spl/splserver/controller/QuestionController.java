package com.spl.splserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spl.splserver.POJO.AnswerCheckDTO;
import com.spl.splserver.POJO.LearnState;
import com.spl.splserver.entity.Question;
import com.spl.splserver.service.LearningService;
import com.spl.splserver.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("question")
public class QuestionController {

    @Autowired
    final QuestionService questionService;
    @Autowired
    final LearningService learningService;

    public QuestionController(QuestionService questionService, LearningService learningService) {
        this.questionService = questionService;
        this.learningService = learningService;
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createQuestion(@RequestBody Question question) throws JsonProcessingException {
        Question result = questionService.createQuestion(question);
        if (result == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        ObjectMapper jsonObject = new ObjectMapper();
        return new ResponseEntity<>(jsonObject.writeValueAsString(question),HttpStatus.OK);
    }


    @GetMapping("{question_id}")
    public ResponseEntity<?> getQuestionById(@PathVariable("question_id") String questionId) throws JsonProcessingException {
        Question question = questionService.getQuestionById(questionId);
        if (question == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        ObjectMapper jsonObject = new ObjectMapper();
        return new ResponseEntity(jsonObject.writeValueAsString(question),HttpStatus.OK);
    }

    @PostMapping(value = "/check",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFields(@RequestBody AnswerCheckDTO answerCheck) throws JsonProcessingException {
        ArrayList answers = (ArrayList) answerCheck.getAnswers();
        String questionId = answerCheck.getQuestionId();
        LearnState result = questionService.checkAnswer(questionId, answers);
        if(result == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        learningService.setRepeatDate(questionId,result);

        ObjectMapper jsonObject = new ObjectMapper();
        return new ResponseEntity(jsonObject.writeValueAsString(result),HttpStatus.OK);
    }
}
