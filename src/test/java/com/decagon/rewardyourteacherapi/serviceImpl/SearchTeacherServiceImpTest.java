package com.decagon.rewardyourteacherapi.serviceImpl;

import com.decagon.rewardyourteacherapi.RewardYourTeacherApiApplication;
import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.repository.UserRepository;
import com.decagon.rewardyourteacherapi.service.SearchTeacherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RewardYourTeacherApiApplication.class)
class SearchTeacherServiceImpTest {
    User user1 = new User( "george", "victim", "test2@gamil.com", "password", Role.TEACHER);
    User user2 = new User( "bukky", "joy", "testing2@gamil.com", "pass", Role.TEACHER);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SearchTeacherService searchTeacherService;

    @Test
    void searchTeacher() {
        List<User>userList = new ArrayList<>();
        searchTeacherService = new SearchTeacherServiceImp(userRepository);
        Assertions.assertEquals(userList,searchTeacherService.searchTeacher("bukky"));

    }
}