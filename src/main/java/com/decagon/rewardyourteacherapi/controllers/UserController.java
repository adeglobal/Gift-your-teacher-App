package com.decagon.rewardyourteacherapi.controllers;

import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.payload.LoginDto;
import com.decagon.rewardyourteacherapi.services.UserService;
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

    @GetMapping("/api/v1/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginDto loginDto){
        System.out.println(loginDto.getEmail());
        return  userService.login(loginDto);
    }


}
