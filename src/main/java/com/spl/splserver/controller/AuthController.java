package com.spl.splserver.controller;

import com.spl.splserver.POJO.UserInfoRequest;
import com.spl.splserver.entity.User;
import com.spl.splserver.service.AuthService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/auth")
public class AuthController {
    final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping(value = "signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signIn(@Valid @RequestBody UserInfoRequest userInfoRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> messages = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(messages,HttpStatus.BAD_REQUEST);
        }
        User user = authService.signIn(userInfoRequest.getUsername(), userInfoRequest.getPassword());
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signUp(@Valid @RequestBody UserInfoRequest userInfoRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> messages = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(messages,HttpStatus.BAD_REQUEST);
        }
        User user = authService.signUp(userInfoRequest.getUsername(), userInfoRequest.getPassword());
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
