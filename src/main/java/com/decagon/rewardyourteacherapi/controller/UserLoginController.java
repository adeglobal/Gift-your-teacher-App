package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.payload.LoginDTO;
import com.decagon.rewardyourteacherapi.service.UserService;
import com.decagon.rewardyourteacherapi.util.Responder;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserLoginController {

    private final UserService userService;

    @PostMapping("/api/v1/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginDTO loginDto){
        return  Responder.okay(userService.login(loginDto));
    }


}
