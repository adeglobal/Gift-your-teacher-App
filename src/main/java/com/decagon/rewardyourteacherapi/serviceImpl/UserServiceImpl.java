package com.decagon.rewardyourteacherapi.serviceImpl;


import com.decagon.rewardyourteacherapi.exception.AuthorizationException;
import com.decagon.rewardyourteacherapi.exception.UserAlreadyExistsException;
import com.decagon.rewardyourteacherapi.exception.UserNotFoundException;
import com.decagon.rewardyourteacherapi.mapper.PayloadToModel;
import com.decagon.rewardyourteacherapi.model.*;
import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.payload.LoginDTO;
import com.decagon.rewardyourteacherapi.payload.UserDTO;
import com.decagon.rewardyourteacherapi.repository.NotificationRepository;
import com.decagon.rewardyourteacherapi.repository.UserRepository;
import com.decagon.rewardyourteacherapi.repository.WalletRepository;
import com.decagon.rewardyourteacherapi.security.JwtService;
import com.decagon.rewardyourteacherapi.service.UserService;
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
import java.security.Principal;
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

    private final WalletRepository walletRepository;
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

    public String authenticateOauth2User(UserDTO request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if(existingUser.isEmpty()){
            User newUser = PayloadToModel.MapRequestToUser(request);
            userRepository.save(newUser);
        }
        return "Bearer " + JwtService.generateToken
                (new org.springframework.security.core.userdetails.User(request.getEmail(), request.getPassword(),
                        new ArrayList<>()));
    }
    public User updateUserProfile (UserDTO userRegistrationDTO, long id){
        System.out.println(userRegistrationDTO + "" + id);
        User newUserDetails =userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user details not fund"));
//        System.out.println(newUserDetails);
        if(userRegistrationDTO.getFirstname()!=null){
            newUserDetails.setFirstName(userRegistrationDTO.getFirstname());
        }
        if (userRegistrationDTO.getLastname()!=null){
            newUserDetails.setLastName(userRegistrationDTO.getLastname());
        }
        if (userRegistrationDTO.getPassword()!=null){
            newUserDetails.setPassword(userRegistrationDTO.getPassword());
        }
        if (userRegistrationDTO.getImageUrl()!= null) {
            newUserDetails.setProfileImage(userRegistrationDTO.getImageUrl());
        }

      return userRepository.save(newUserDetails);

    }

    @Override
    public BigDecimal getCurrentWalletBalance(Long user_id) {
        Optional<User> user = userRepository.findById(user_id);

        if (user.isPresent()){
            Optional<Wallet> wallet = walletRepository.findWalletById(user.get().getId());

            if(wallet.isPresent()){

                return wallet.get().getTotal();
            }
        }
        return new BigDecimal(0.0);
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
        List<UserDTO> userDTOList = paged.getContent().stream().map(PayloadToModel::MapUserToDTO).collect(Collectors.toList());
        return new PageImpl<>(userDTOList, paging, paged.getTotalElements());
    }

    @Override
    public Page<User> retrieveTeachers(int page, int size) {
        return userRepository.findUsersByRole(PageRequest.of(page, size),Role.TEACHER );
    }
    @Override
   public User viewTeacherProfileByEmail(String email){
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String email = ((UserDetails)principal).getUsername();

        return userRepository.findUserByEmailAndRole(email, Role.TEACHER).orElseThrow(()->new RuntimeException("User not found"));
    }

    @Override
    public User viewTeacherProfileById(Long id) {
        return userRepository.findUserByIdAndRole(id,Role.TEACHER).orElseThrow(()->new RuntimeException("User not found"));
    }

    @Override
    public Notification teacherAppreciatesStudent(Long userId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)principal).getUsername();

        Optional<User> teacher = userRepository.findUserByEmailAndRole(email, Role.TEACHER);
        String teacherDetails = teacher.get().getFirstName() + " " + teacher.get().getLastName();
        User student = userRepository.findUserByIdAndRole(userId, Role.STUDENT).get();
        String messageToStudent = String.format("%s appreciated you \uD83D\uDC4D", teacherDetails);
        Notification notification = new Notification(messageToStudent, student);
        Notification notification1 = notificationRepository.save(notification);
       return PayloadToModel.NotificationMapper(notification1);
    }

}
