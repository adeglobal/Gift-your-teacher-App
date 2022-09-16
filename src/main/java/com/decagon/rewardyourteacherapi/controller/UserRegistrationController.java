package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.pojos.UserRegistrationRequest;
import com.decagon.rewardyourteacherapi.service.UserRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/register")
@AllArgsConstructor
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    @PostMapping(path  ="/student")
    public String registerLocal(@RequestBody UserRegistrationRequest request) {
        request.setRole(Role.STUDENT);
        return userRegistrationService.register(request);
    }

    @PostMapping(path  ="/teacher")
    public String registerGoogle(@RequestBody UserRegistrationRequest request) {
        request.setRole(Role.TEACHER);
        return userRegistrationService.register(request);
    }
}
