package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.payload.UserDTO;
import com.decagon.rewardyourteacherapi.service.UserService;
import com.decagon.rewardyourteacherapi.util.Responder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/edit/{id}")
    public ResponseEntity<APIResponse> editStudentProfile(@PathVariable(name = "id") long id, @RequestBody UserDTO request) {
        request.setRole(Role.STUDENT);
        return Responder.okay(userService.updateUserProfile(request, id));

    }
}
