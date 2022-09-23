package com.decagon.rewardyourteacherapi.serviceImpl;


import com.decagon.rewardyourteacherapi.exception.AuthorizationException;
import com.decagon.rewardyourteacherapi.exception.UserAlreadyExistsException;
import com.decagon.rewardyourteacherapi.exception.UserNotFoundException;
import com.decagon.rewardyourteacherapi.mapper.PayloadToModel;
import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.model.School;
import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.model.Wallet;
import com.decagon.rewardyourteacherapi.payload.LoginDTO;
import com.decagon.rewardyourteacherapi.payload.UserDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    private final WalletRepository walletRepository;
    private final PasswordEncoder passwordEncoder;


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
        User dbUser = userRepository.save(user);
        Wallet wallet = new Wallet(dbUser, new BigDecimal(0));
        walletRepository.save(wallet);
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
        User dBUser =userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user details not fund"));
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)principal).getUsername();
        if(dBUser.getEmail().equals(email)) {
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
            return userRepository.save(dBUser);
        }
        else{
            throw new AuthorizationException("this is not your profile") ;
        }
    }

    @Override
    public BigDecimal getCurrentWalletBalance() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)principal).getUsername();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("user details not fund"));
        Optional<Wallet> wallet = walletRepository.findWalletById(user.getId());
        if(wallet.isPresent()){
            return wallet.get().getTotal();
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
    public User viewTeacherProfile(Long id) {
        return userRepository.findUserByIdAndRole(id,Role.TEACHER).orElseThrow(()->new RuntimeException("User not found"));
    }

    public List<UserDTO> searchTeacher(String name){
        List<User> list = userRepository.findUsersByRoleAndFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(Role.TEACHER,name, name);
        return  list.stream().map(PayloadToModel::MapUserToDTO).collect(Collectors.toList());
    }

}
