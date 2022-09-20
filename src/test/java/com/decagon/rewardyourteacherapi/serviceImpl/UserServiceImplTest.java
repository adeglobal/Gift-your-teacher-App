package com.decagon.rewardyourteacherapi.serviceImpl;

import com.decagon.rewardyourteacherapi.RewardYourTeacherApiApplication;
import com.decagon.rewardyourteacherapi.exception.UserAlreadyExistsException;
import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.UserRegistrationDTO;
import com.decagon.rewardyourteacherapi.repository.UserRepository;
import com.decagon.rewardyourteacherapi.security.JwtService;
import com.decagon.rewardyourteacherapi.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.decagon.rewardyourteacherapi.model.Role.TEACHER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RewardYourTeacherApiApplication.class)

class UserServiceImplTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @MockBean
    AuthenticationManager authenticationManager;
    @MockBean
    JwtService jwtService;
    @MockBean
    PasswordEncoder passwordEncoder;

    User user2 = new User( "george", "victim", "test2@gamil.com", "password", TEACHER);




    @Test
    void updateUserProfile(){
        userService = new UserServiceImpl( authenticationManager, jwtService, userRepository, passwordEncoder);
        User uUser = new User("george", "victim",passwordEncoder.encode("this"), "bla.png", Role.STUDENT);
        userRepository.save(uUser);
        UserRegistrationDTO modified = new UserRegistrationDTO("King george", "Duren", "newimage.png");
        Assertions.assertEquals("King george", userService.updateUserProfile(modified, 1L).getFirstName());
    }


    @Test
    void SignUpUser(){
        userService =  new UserServiceImpl(authenticationManager, jwtService, userRepository, passwordEncoder);
        Assertions.assertEquals(user2, userService.signUpUser(user2));
        Exception exception = assertThrows(RuntimeException.class, () -> userService.signUpUser(user2));

        String expectedMessage = "Email test2@gamil.com has been taken";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }



}