//package com.decagon.rewardyourteacherapi.serviceImpl;
//
//import com.decagon.rewardyourteacherapi.RewardYourTeacherApiApplication;
//import com.decagon.rewardyourteacherapi.exception.UserAlreadyExistsException;
//import com.decagon.rewardyourteacherapi.model.Role;
//import com.decagon.rewardyourteacherapi.model.User;
//import com.decagon.rewardyourteacherapi.repository.UserRepository;
//import com.decagon.rewardyourteacherapi.security.JwtService;
//import com.decagon.rewardyourteacherapi.service.UserService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.function.Executable;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.jupiter.api.Assertions.*;
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = RewardYourTeacherApiApplication.class)
//
//class UserServiceImplTest {
//
//    @Autowired
//    UserService userService;
//    @Autowired
//    UserRepository userRepository;
//    @MockBean
//    AuthenticationManager authenticationManager;
//    @MockBean
//    JwtService jwtService;
//    @MockBean
//    PasswordEncoder passwordEncoder;
//
//    User user2 = new User( "george", "victim", "test2@gamil.com", "password", Role.TEACHER);
//
//    @Test
//    void SignUpUser(){
//        userService =  new UserServiceImpl(authenticationManager, jwtService, userRepository, passwordEncoder);
//        Assertions.assertEquals(user2, userService.signUpUser(user2));
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            userService.signUpUser(user2);
//        });
//
//        String expectedMessage = "Email test2@gamil.com has been taken";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//
//    }
//
//}