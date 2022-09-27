package com.decagon.rewardyourteacherapi.service;

import com.decagon.rewardyourteacherapi.model.Notification;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.LoginDTO;
import org.springframework.data.domain.Page;
import com.decagon.rewardyourteacherapi.payload.UserDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;

@Service
public interface UserService {

    String login(LoginDTO loginDto);

    User signUpUser(User user);

    String authenticateOauth2User(UserDTO request);

    Page<UserDTO> getSchoolTeachers(Long id, int page, int size);

    User updateUserProfile(UserDTO request, long id);

    BigDecimal getCurrentWalletBalance(Long id);

    Page<User> retrieveTeachers(int page, int size);

    User viewTeacherProfileByEmail(String email);

    User viewTeacherProfileById(Long id);

    Notification teacherAppreciatesStudent(Long userId);



}


