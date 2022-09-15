package com.decagon.rewardyourteacherapi.service;

import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.pojos.EmailValidator;
import com.decagon.rewardyourteacherapi.pojos.UserRegistrationRequest;
//import com.decagon.rewardyourteacherapi.pojos.EmailSender;
import com.decagon.rewardyourteacherapi.model.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserRegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
//    private final EmailSender emailSender;

    public String register(UserRegistrationRequest request) {
        boolean isValidEmail = emailValidator.
                test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        String token = userService.signUpUser(
                new User(
                        request.getFirstname(),
                        request.getLastname(),
                        request.getEmail(),
                        request.getPassword(),
                        Role.STUDENT
                )
        );


        return token;
   }
}
