package com.decagon.rewardyourteacherapi.serviceImpl;


import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.payload.LoginDto;
import com.decagon.rewardyourteacherapi.repository.UserRepository;
import com.decagon.rewardyourteacherapi.security.JwtService;
import com.decagon.rewardyourteacherapi.services.UserService;
import com.decagon.rewardyourteacherapi.util.Responder;
import com.decagon.rewardyourteacherapi.util.Utility;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    private final UserRepository userRepository;

    private  final Utility util;
    private final Responder responder;

    private final PasswordEncoder passwordEncoder;



    public ResponseEntity <APIResponse> login(LoginDto loginDto){
        Authentication auth= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        if(auth.isAuthenticated()){
            String token="Bearer "+jwtService.generateToken(new org.springframework.security.core.userdetails.User(loginDto.getEmail(),loginDto.getPassword(),new ArrayList<>()));
            return  responder.Okay(token);
        }else{
            return  responder.UnAuthorize("Not Authenticated ");
        }
    }



}
