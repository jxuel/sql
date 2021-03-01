package com.spl.splserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.spl.splserver.POJO.QuestionSetCreateRequest;
import com.spl.splserver.entity.Question;
import com.spl.splserver.entity.QuestionSet;
import com.spl.splserver.service.QuestionSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
/*
    QuestionSetController

    Endpoint:
        /learnList
            GET                  get sets by params
                params:              ownerId
            POST                 create a set
                params:              ownerId

        TODO: paging, search by time
        /learnList/{set_id}
            GET                  get a set by id
            DELETE               delete a set by id
        TODO: modify

 */

@Controller
@RequestMapping("learnList")
public class QuestionSetController {
    @Autowired
    final QuestionSetService questionSetService;

    public QuestionSetController(QuestionSetService questionSetService) {
        this.questionSetService = questionSetService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createQuestionSet(@Valid @RequestBody QuestionSetCreateRequest requestBody, BindingResult bindingResult) throws JsonProcessingException {
        if(bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }

        QuestionSet createQuestionSet = new QuestionSet();
        createQuestionSet.setName(requestBody.getName());
        createQuestionSet.setOwnerId(requestBody.getOwnerId());

        QuestionSet questionSet = questionSetService.createSingleQuestionSet(createQuestionSet);

        if (questionSet == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return new ResponseEntity<>(objectMapper.writeValueAsString(questionSet),HttpStatus.OK);
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

    @GetMapping(value = "{set_id}/test",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTestSet(
            @RequestParam(value = "quantity") Integer quantity,
            @PathVariable("set_id") String setId) throws JsonProcessingException {
        List<Question> questions = questionSetService.getTestSet(setId, quantity);

        if (questions == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        ObjectMapper objectMapper = new ObjectMapper();

        return new ResponseEntity(objectMapper.writeValueAsString(questions),HttpStatus.OK);
    }
    @DeleteMapping(value = "{set_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteQuestionSet(@PathVariable("set_id") String setId) {
        boolean result = questionSetService.deleteQuestionSet(setId);

        return new ResponseEntity(result,HttpStatus.OK);
    }


}
