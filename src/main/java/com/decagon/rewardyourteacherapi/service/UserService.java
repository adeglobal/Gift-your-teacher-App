package com.decagon.rewardyourteacherapi.service;

import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.LoginDTO;
import com.decagon.rewardyourteacherapi.payload.TeacherResponse;
import com.decagon.rewardyourteacherapi.payload.UserRegistrationDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    String login(LoginDTO loginDto);

    User signUpUser(User user);

    String authenticateOauth2User(UserRegistrationDTO request);

    List<TeacherResponse> retrieveTeachers(int page, int size);
}


