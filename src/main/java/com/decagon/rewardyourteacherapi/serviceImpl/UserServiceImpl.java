package com.decagon.rewardyourteacherapi.serviceImpl;


import com.decagon.rewardyourteacherapi.exception.AuthorizationException;
import com.decagon.rewardyourteacherapi.exception.UserAlreadyExistsException;
import com.decagon.rewardyourteacherapi.mapper.PayloadToModel;
import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.model.School;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.LoginDTO;
import com.decagon.rewardyourteacherapi.payload.UserRegistrationDTO;
import com.decagon.rewardyourteacherapi.repository.UserRepository;
import com.decagon.rewardyourteacherapi.security.JwtService;
import com.decagon.rewardyourteacherapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public String login(LoginDTO loginDto){
        Authentication auth= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        if(auth.isAuthenticated()){
            return "Bearer "+jwtService.generateToken(new org.springframework.security.core.userdetails.User(loginDto.getEmail(),loginDto.getPassword(),new ArrayList<>()));
        }else{
            throw new AuthorizationException("Not Authenticated ");
        }
    }

    public User signUpUser(User user) {
        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();

        if (userExists) {
            throw new UserAlreadyExistsException(String.format("Email %s has been taken", user.getEmail()));
        }
        String encodedPassword = passwordEncoder
                .encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return user;
    }

    public String authenticateOauth2User(UserRegistrationDTO request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if(existingUser.isEmpty()){
            User newUser = PayloadToModel.MapRequestToUser(request);
            userRepository.save(newUser);
        }
        return "Bearer " + JwtService.generateToken
                (new org.springframework.security.core.userdetails.User(request.getEmail(), request.getFirstname(),
                        new ArrayList<>()));
    }

    @Override
    public Page<User> getSchoolTeachers(Long id, int page, int size) {
        if(page < 0){
            page = 0;
        }
        if(size < 1){
            size = 1;
        }
        Pageable paging = PageRequest.of(page, size);
        School school = new School(id);
        return userRepository.findAllBySchoolAndRole(school, Role.TEACHER, paging);
    }

}
