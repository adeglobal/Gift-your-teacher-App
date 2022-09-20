package com.decagon.rewardyourteacherapi.service;

import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.LoginDTO;
import com.decagon.rewardyourteacherapi.payload.UserRegistrationDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    String login(LoginDTO loginDto);

    User signUpUser(User user);

    String authenticateOauth2User(UserRegistrationDTO request);

//    User getProfileById( long id);

    User updateUserProfile(UserRegistrationDTO request, long id);
}


