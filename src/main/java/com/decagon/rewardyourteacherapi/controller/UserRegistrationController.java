package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.mapper.RequestToUser;
import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.payload.UserRegistrationRequest;
import com.decagon.rewardyourteacherapi.service.UserService;
import com.decagon.rewardyourteacherapi.util.Responder;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/register")
@AllArgsConstructor
public class UserRegistrationController {

    private final UserService userService;

    @PostMapping(path  ="/student")
    public ResponseEntity<APIResponse> registerLocal(@RequestBody UserRegistrationRequest request) {
        request.setRole(Role.STUDENT);
        return Responder.okay(userService.signUpUser(RequestToUser.MapToUser(request)));
    }

    @PostMapping(path  ="/teacher")
    public ResponseEntity<APIResponse> registerGoogle(@RequestBody UserRegistrationRequest request) {
        request.setRole(Role.TEACHER);
        return Responder.okay(userService.signUpUser(RequestToUser.MapToUser(request)));
    }
}
