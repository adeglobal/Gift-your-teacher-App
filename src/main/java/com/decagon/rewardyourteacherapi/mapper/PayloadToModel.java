package com.decagon.rewardyourteacherapi.mapper;

import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.UserRegistrationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PayloadToModel {
    public static User MapRequestToUser(UserRegistrationDTO request ){
       User user  = new User();
       if(request.getFirstname() != null){
           user.setFirstName(request.getFirstname());
       }
       if(request.getLastname() !=  null){
           user.setLastName(request.getLastname());
       }
       if(request.getEmail() != null ){
           user.setEmail(request.getEmail());
       }
       if(request.getPassword() != null){
           user.setPassword(request.getPassword());
       }
       if(request.getRole() != null){
           user.setRole(request.getRole());
       }
       if(request.getImageUrl() != null){
           user.setProfileImage(request.getImageUrl());
       }
       return user;
    }
}
