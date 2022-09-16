package com.decagon.rewardyourteacherapi.services;

import com.decagon.rewardyourteacherapi.payload.LoginDto;
import org.springframework.http.ResponseEntity;

public interface UserService {



    String login(LoginDto loginDto);
}


