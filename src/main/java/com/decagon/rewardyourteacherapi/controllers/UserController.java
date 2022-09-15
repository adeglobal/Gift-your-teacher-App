package com.decagon.rewardyourteacherapi.controllers;

import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.payload.LoginDto;
import com.decagon.rewardyourteacherapi.services.UserService;
import com.decagon.rewardyourteacherapi.util.Responder;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final Responder responder;

    @PostMapping("/api/v1/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginDto loginDto){
        return  responder.Okay(userService.login(loginDto));
    }


}
