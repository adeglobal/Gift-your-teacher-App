package com.decagon.rewardyourteacherapi.mapper;

import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.UserRegistrationRequest;

public class RequestToUser {
    public static User MapToUser(UserRegistrationRequest request){
        return new User(request.getFirstname(), request.getFirstname(), request.getEmail(), request.getPassword(), request.getRole());
    }
}