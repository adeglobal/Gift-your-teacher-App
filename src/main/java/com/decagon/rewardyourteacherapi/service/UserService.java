package com.decagon.rewardyourteacherapi.service;

import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.LoginDTO;
import org.springframework.data.domain.Page;
import com.decagon.rewardyourteacherapi.payload.UserDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface UserService {

    String login(LoginDTO loginDto);

    UserDTO signUpUser(User user);

    String authenticateOauth2User(UserDTO request);

    Page<UserDTO> getSchoolTeachers(Long id, int page, int size);

    UserDTO updateUserProfile(UserDTO request);

    BigDecimal getCurrentWalletBalance();

    Page<UserDTO> retrieveTeachers(int page, int size);

    UserDTO viewTeacherProfile(Long id);

    List<UserDTO> searchTeacher(String name);

}


