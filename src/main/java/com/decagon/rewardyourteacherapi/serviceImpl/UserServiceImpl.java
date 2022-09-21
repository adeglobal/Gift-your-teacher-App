package com.decagon.rewardyourteacherapi.serviceImpl;


import com.decagon.rewardyourteacherapi.exception.AuthorizationException;
import com.decagon.rewardyourteacherapi.exception.UserAlreadyExistsException;
import com.decagon.rewardyourteacherapi.exception.UserNotFoundException;
import com.decagon.rewardyourteacherapi.mapper.PayloadToModel;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.model.Wallet;
import com.decagon.rewardyourteacherapi.payload.LoginDTO;
import com.decagon.rewardyourteacherapi.payload.UserRegistrationDTO;
import com.decagon.rewardyourteacherapi.repository.UserRepository;
import com.decagon.rewardyourteacherapi.repository.WalletRepository;
import com.decagon.rewardyourteacherapi.security.JwtService;
import com.decagon.rewardyourteacherapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

@Service
@ToString
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    private final WalletRepository walletRepository;
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
                (new org.springframework.security.core.userdetails.User(request.getEmail(), request.getPassword(),
                        new ArrayList<>()));
    }
    public User updateUserProfile (UserRegistrationDTO userRegistrationDTO, long id){
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

}
