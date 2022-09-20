package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.mapper.PayloadToModel;
import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.payload.UserRegistrationDTO;
import com.decagon.rewardyourteacherapi.service.UserService;
import com.decagon.rewardyourteacherapi.util.Responder;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/register")
@AllArgsConstructor
public class UserRegistrationController {

    private final UserService userService;

    @PostMapping(path = "/student")
    public ResponseEntity<APIResponse> registerLocal(@RequestBody UserRegistrationDTO request) {
        request.setRole(Role.STUDENT);
        return Responder.okay(userService.signUpUser(PayloadToModel.MapRequestToUser(request)));
    }

    @PostMapping("/student/callback")
    public ResponseEntity<APIResponse> authenticateOauth2User(@RequestBody UserRegistrationDTO request) {
        request.setRole(Role.STUDENT);
        return Responder.okay(userService.authenticateOauth2User(request));
    }

    @PostMapping(path = "/teacher")
    public ResponseEntity<APIResponse> registerGoogle(@RequestBody UserRegistrationDTO request) {
        request.setRole(Role.TEACHER);
        return Responder.okay(userService.signUpUser(PayloadToModel.MapRequestToUser(request)));
    }

    @PostMapping("/teacher/callback")
    public ResponseEntity<APIResponse> authenticateOauth2Teacher(@RequestBody UserRegistrationDTO request) {
        request.setRole(Role.TEACHER);
        return Responder.okay(userService.authenticateOauth2User(request));
    }

    @PostMapping(value = "/edit/teacher{id}")
    public ResponseEntity<APIResponse> editProfile(@PathVariable(name = "id") long id, @RequestBody UserRegistrationDTO request) {
        request.setRole(Role.TEACHER);
        long userId = (id);
        return Responder.okay(userService.updateUserProfile(request, userId));
    }

    @PostMapping(value = "/edit/student/{id}")
    public ResponseEntity<APIResponse> editStudentProfile(@PathVariable(name = "id") long id, @RequestBody UserRegistrationDTO request) {
        request.setRole(Role.STUDENT);
        long userId = (id);
        return Responder.okay(userService.updateUserProfile(request, userId));

    }
}
