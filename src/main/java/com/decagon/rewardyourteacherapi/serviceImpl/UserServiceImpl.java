package com.decagon.rewardyourteacherapi.serviceImpl;


import com.decagon.rewardyourteacherapi.exception.AuthorizationException;
import com.decagon.rewardyourteacherapi.exception.UserAlreadyExistsException;
import com.decagon.rewardyourteacherapi.exception.UserNotFoundException;
import com.decagon.rewardyourteacherapi.mapper.PayloadToModel;
import com.decagon.rewardyourteacherapi.model.*;
import com.decagon.rewardyourteacherapi.enums.Role;
import com.decagon.rewardyourteacherapi.model.School;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.LoginDTO;
import com.decagon.rewardyourteacherapi.payload.UserDTO;
import com.decagon.rewardyourteacherapi.repository.NotificationRepository;
import com.decagon.rewardyourteacherapi.repository.UserRepository;
import com.decagon.rewardyourteacherapi.security.JwtService;
import com.decagon.rewardyourteacherapi.service.UserService;
import com.decagon.rewardyourteacherapi.util.ContextEmail;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import lombok.ToString;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ToString
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final NotificationRepository notificationRepository;


    public String login(LoginDTO loginDto){
        Authentication auth= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        if(auth.isAuthenticated()){
            return "Bearer " + JwtService.generateToken
            (new org.springframework.security.core.userdetails.User(loginDto.getEmail(), loginDto.getPassword(),
                    new ArrayList<>()));
        }else{
            throw new AuthorizationException("Not Authenticated ");
        }
    }

    public UserDTO signUpUser(User user) {
        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();

        if (userExists) {
            throw new UserAlreadyExistsException(String.format("Email %s has been taken", user.getEmail()));
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return PayloadToModel.mapUserToDTO(userRepository.save(user));
    }

    public String authenticateOauth2User(UserDTO request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if(existingUser.isEmpty()){
            User newUser = PayloadToModel.mapRequestToUser(request);
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            userRepository.save(newUser);
        }
        return "Bearer " + JwtService.generateToken
                (new org.springframework.security.core.userdetails.User(request.getEmail(), request.getPassword(),
                        new ArrayList<>()));
    }
    public UserDTO updateUserProfile (UserDTO userRegistrationDTO){
        User dBUser =userRepository.findByEmail(ContextEmail.getEmail()).orElseThrow(() -> new UserNotFoundException("user details not fund"));
        if (userRegistrationDTO.getFirstname() != null) {
            dBUser.setFirstName(userRegistrationDTO.getFirstname());
        }
        if (userRegistrationDTO.getLastname() != null) {
            dBUser.setLastName(userRegistrationDTO.getLastname());
        }
        if (userRegistrationDTO.getPassword() != null) {
            dBUser.setPassword(userRegistrationDTO.getPassword());
        }
        if (userRegistrationDTO.getImageUrl() != null) {
            dBUser.setProfileImage(userRegistrationDTO.getImageUrl());
        }
        return PayloadToModel.mapUserToDTO(userRepository.save(dBUser));
    }

    @Override
    public BigDecimal getCurrentWalletBalance() {
        User user = userRepository.findByEmail(ContextEmail.getEmail()).orElseThrow(() -> new UserNotFoundException("user details not fund"));
        return user.getWallet();
    }

    @Override
    public Page<UserDTO> getSchoolTeachers(Long id, int page, int size) {
        if(page < 0){
            page = 0;
        }
        if(size < 1){
            size = 1;
        }
        Pageable paging = PageRequest.of(page, size);
        School school = new School(id);
        Page<User> paged = userRepository.findAllBySchoolAndRole(school, Role.TEACHER, paging);
        List<UserDTO> userDTOList = paged.getContent().stream().map(PayloadToModel::mapUserToDTO).collect(Collectors.toList());
        return new PageImpl<>(userDTOList, paging, paged.getTotalElements());
    }

    @Override
    public Page<UserDTO> retrieveTeachers(int page, int size) {
        Page<User> userPage= userRepository.findUsersByRole(PageRequest.of(page, size),Role.TEACHER );
        List<UserDTO> userDTOList = userPage.stream().map(PayloadToModel::mapUserToDTO).collect(Collectors.toList());
        return new PageImpl<>(userDTOList, PageRequest.of(page, size), userPage.getTotalElements());
    }

    @Override
    public UserDTO viewTeacherProfile(Long id) {
        return PayloadToModel.mapUserToDTO(userRepository.findUserByIdAndRole(id,Role.TEACHER).orElseThrow(()->new RuntimeException("User not found")));
    }

    public List<UserDTO> searchTeacher(String name){
        List<User> list = userRepository.findUsersByRoleAndFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(Role.TEACHER,name, name);
        return  list.stream().map(PayloadToModel::mapUserToDTO).collect(Collectors.toList());
    }

    @Override
    public Notification teacherAppreciatesStudent(Long userId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)principal).getUsername();

        User teacher = userRepository.findUserByEmailAndRole(email, Role.TEACHER).orElseThrow(()-> new UserNotFoundException("user not found"));
        String teacherDetails = teacher.getFirstName() + " " + teacher.getLastName();
        User student = userRepository.findUserByIdAndRole(userId, Role.STUDENT).orElseThrow(() -> new UserNotFoundException("user not found"));
        String messageToStudent = String.format("%s appreciated you \uD83D\uDC4D", teacherDetails);
        Notification notification = new Notification( student, messageToStudent);
        Notification notification1 = notificationRepository.save(notification);
       return PayloadToModel.NotificationMapper(notification1);
    }

}
