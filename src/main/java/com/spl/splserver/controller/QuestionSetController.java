package com.spl.splserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.spl.splserver.entity.Question;
import com.spl.splserver.entity.QuestionSet;
import com.spl.splserver.service.QuestionSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
/*
    QuestionSetController

    Endpoint:
        /learnList              get sets by params
            params:              ownerId
        TODO: paging, search by time
        /learnList/{set_id}     get a set by id

 */

@Controller
@RequestMapping("learnList")
public class QuestionSetController {
    @Autowired
    final QuestionSetService questionSetService;

    public QuestionSetController(QuestionSetService questionSetService) {
        this.questionSetService = questionSetService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = {"ownerId"})
    public ResponseEntity<?> getQuestionSets(@RequestParam String ownerId) throws JsonProcessingException {
        List<QuestionSet> result = questionSetService.getAllQuestionSetsByUser(ownerId);
        if(result == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonArray = objectMapper.writeValueAsString(result);
        return new ResponseEntity<>(jsonArray,HttpStatus.OK);
    }

    @GetMapping(value = "{set_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getQuestionSet(@PathVariable("set_id") String setId) throws JsonProcessingException {
        QuestionSet questionSet = questionSetService.getQuestionSet(setId);

        if (questionSet == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        ObjectMapper objectMapper = new ObjectMapper();

        return new ResponseEntity(objectMapper.writeValueAsString(questionSet),HttpStatus.OK);
    }


}
