package com.decagon.rewardyourteacherapi.serviceImpl;


import com.decagon.rewardyourteacherapi.exception.AuthorizationException;
import com.decagon.rewardyourteacherapi.payload.LoginDto;
import com.decagon.rewardyourteacherapi.security.JwtService;
import com.decagon.rewardyourteacherapi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;


    public String login(LoginDto loginDto){
        Authentication auth= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        if(auth.isAuthenticated()){
            String token="Bearer "+jwtService.generateToken(new org.springframework.security.core.userdetails.User(loginDto.getEmail(),loginDto.getPassword(),new ArrayList<>()));
            return  token;
        }else{
            throw new AuthorizationException("Not Authenticated ");
        }
    }



}
