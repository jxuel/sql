package com.spl.splserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@Controller
@RequestMapping("learnList")
public class QuestionSetController {
    @Autowired
    QuestionSetService questionSetService;
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
