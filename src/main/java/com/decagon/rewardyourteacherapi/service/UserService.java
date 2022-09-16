package com.decagon.rewardyourteacherapi.service;

import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.payload.LoginDto;
import org.springframework.http.ResponseEntity;

public interface UserService {



    String login(LoginDto loginDto);

    ResponseEntity<APIResponse> signUpUser(User user);
}


