package com.decagon.rewardyourteacherapi.service;

import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.LoginDto;
import com.decagon.rewardyourteacherapi.payload.UserRegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    String login(LoginDto loginDto);

    User signUpUser(User user);

    String authenticateOauth2User(UserRegistrationRequest request);
}


