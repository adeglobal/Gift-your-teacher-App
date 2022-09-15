package com.decagon.rewardyourteacherapi.controllers;

import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.payload.LoginDto;
import com.decagon.rewardyourteacherapi.services.UserService;
import com.decagon.rewardyourteacherapi.util.Responder;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/v1/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginDto loginDto){
        return  Responder.okay(userService.login(loginDto));
    }


}