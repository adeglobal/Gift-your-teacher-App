package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.payload.LoginDTO;
import com.decagon.rewardyourteacherapi.service.UserService;
import com.decagon.rewardyourteacherapi.util.Responder;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserLoginController {

    private final UserService userService;

    @PostMapping("/api/v1/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginDTO loginDto){
        return  Responder.okay(userService.login(loginDto));
    }


    //@RequestMapping(value = "/viewSingleTeacher", method = RequestMethod.GET)
    @GetMapping(value = "/api/v1/viewSingleTeacherByEmail")
    public ResponseEntity<APIResponse> viewTeacherByEmail(@RequestParam String email) {
        return Responder.okay(userService.viewTeacherProfileByEmail(email));
    }

    @GetMapping(value = "/api/v1/viewSingleTeacherById")
    public ResponseEntity<APIResponse> viewTeacherById(@RequestParam Long id){
        return Responder.okay((userService.viewTeacherProfileById(id)));
    }

}
