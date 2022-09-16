package com.decagon.rewardyourteacherapi.service;

//import com.decagon.rewardyourteacherapi.UserRepository;
import com.decagon.rewardyourteacherapi.model.GoogleAuthUser;
import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.repository.UserRepository;
import com.decagon.rewardyourteacherapi.security.JwtService;
import com.decagon.rewardyourteacherapi.util.Responder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserOauthService {
    private final Responder responder;
    @Autowired
    private UserRepository repo;

    public UserOauthService() {
        responder = new Responder<>();
    }



    public ResponseEntity<APIResponse> authenticateOauth2Student(GoogleAuthUser authPrincipal) {
        Optional<User> existingUser = repo.findByEmail(authPrincipal.getEmail());
        // if user doesn't exist in db, register
        if(!existingUser.isPresent()){
            User newStudent = new User();
            newStudent.setEmail(authPrincipal.getEmail());
            newStudent.setFirstName(authPrincipal.getFirstName());
            newStudent.setLastName(authPrincipal.getLastName());
            newStudent.setProfileImage(authPrincipal.getImageUrl());
            newStudent.setRole(Role.STUDENT);
            repo.save(newStudent);


        }

            String token = "Bearer " + JwtService.generateToken
                    (new org.springframework.security.core.userdetails.User(authPrincipal.getEmail(), authPrincipal.getFirstName(),
                            new ArrayList<>()));
            return  responder.Okay(token);

    }

    public ResponseEntity<APIResponse> authenticateOauth2Teacher(GoogleAuthUser authPrincipal) {
        Optional<User> existingUser = repo.findByEmail(authPrincipal.getEmail());
        // if user doesn't exist in db, register
        if(!existingUser.isPresent()){
            User newTeacher = new User();
            newTeacher.setEmail(authPrincipal.getEmail());
            newTeacher.setFirstName(authPrincipal.getFirstName());
            newTeacher.setLastName(authPrincipal.getLastName());
            newTeacher.setProfileImage(authPrincipal.getImageUrl());
            newTeacher.setRole(Role.TEACHER);
            repo.save(newTeacher);


        }

        String token = "Bearer " + JwtService.generateToken
                (new org.springframework.security.core.userdetails.User(authPrincipal.getEmail(), authPrincipal.getFirstName(),
                        new ArrayList<>()));
        return  responder.Okay(token);

    }
}



