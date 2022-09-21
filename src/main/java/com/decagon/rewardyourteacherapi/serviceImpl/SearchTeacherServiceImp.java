package com.decagon.rewardyourteacherapi.serviceImpl;

import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.repository.UserRepository;
import com.decagon.rewardyourteacherapi.service.SearchTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SearchTeacherServiceImp implements SearchTeacherService {
    private final UserRepository userRepository;
    @Autowired
    public SearchTeacherServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> searchTeacher(String name){
        return userRepository.findUsersByRoleAndFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(Role.TEACHER,name, name);

    }
}
