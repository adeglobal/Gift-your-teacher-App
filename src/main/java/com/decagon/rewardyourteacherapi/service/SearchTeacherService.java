package com.decagon.rewardyourteacherapi.service;

import com.decagon.rewardyourteacherapi.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SearchTeacherService {

    List<User> searchTeacher(String name);
}
