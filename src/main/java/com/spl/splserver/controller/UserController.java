package com.spl.splserver.controller;

import com.spl.splserver.entity.QuestionSet;
import com.spl.splserver.service.QuestionSetService;
import com.spl.splserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

}
