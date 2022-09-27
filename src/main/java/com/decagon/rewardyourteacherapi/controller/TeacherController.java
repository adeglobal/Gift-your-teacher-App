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
@RequestMapping("/api/v1/teacher")
public class TeacherController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/edit/{id}")
    public ResponseEntity<APIResponse> editProfile(@PathVariable(name = "id") long id, @RequestBody UserDTO request) {
        request.setRole(Role.TEACHER);
        return Responder.okay(userService.updateUserProfile(request, id));
    }
    @PostMapping("/appreciate/{id}")
    public ResponseEntity<APIResponse> appreciateStudent(@PathVariable(name = "id") Long id){
        return Responder.okay(userService.teacherAppreciatesStudent(id));
    }
}
