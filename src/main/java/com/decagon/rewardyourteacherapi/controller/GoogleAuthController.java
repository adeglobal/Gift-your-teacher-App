package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.model.GoogleAuthUser;
import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.payload.APIResponse;

import com.decagon.rewardyourteacherapi.service.UserOauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoogleAuthController {

    private final UserOauthService oauthService;

    public GoogleAuthController(UserOauthService oauthService) {
        this.oauthService = oauthService;
    }

    @PostMapping("/api/oauth2/student/callback")
    public ResponseEntity<APIResponse> authenticateOauth2User(@RequestBody GoogleAuthUser principal){
        return oauthService.authenticateOauth2User(principal,Role.STUDENT);
    }

    @PostMapping("/api/oauth2/teacher/callback")
    public ResponseEntity<APIResponse> authenticateOauth2Teacher(@RequestBody GoogleAuthUser principal){
        return oauthService.authenticateOauth2User(principal,Role.TEACHER);
    }
}
