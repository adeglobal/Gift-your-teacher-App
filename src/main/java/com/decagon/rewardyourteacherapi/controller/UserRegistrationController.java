package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.mapper.PayloadToModel;
import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.payload.UserDTO;
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

    @PostMapping(path  ="/student")
    public ResponseEntity<APIResponse> registerLocal(@RequestBody UserDTO request) {
        request.setRole(Role.STUDENT);
        return Responder.okay(userService.signUpUser(PayloadToModel.MapRequestToUser(request)));
    }

    @PostMapping("/student/callback")
    public ResponseEntity<APIResponse> authenticateOauth2User(@RequestBody UserDTO request){
        request.setRole(Role.STUDENT);
        request.setPassword("");
        System.out.println(request.getFirstname()+" "+request.getLastname());
        return Responder.okay(userService.authenticateOauth2User(request));
    }

    @PostMapping(path  ="/teacher")
    public ResponseEntity<APIResponse> registerGoogle(@RequestBody UserDTO request) {
        request.setRole(Role.TEACHER);
        return Responder.okay(userService.signUpUser(PayloadToModel.MapRequestToUser(request)));
    }

    @PostMapping("/teacher/callback")
    public ResponseEntity<APIResponse> authenticateOauth2Teacher(@RequestBody UserDTO request){
        request.setRole(Role.TEACHER);
        request.setPassword("");
        return Responder.okay(userService.authenticateOauth2User(request));
    }
}
