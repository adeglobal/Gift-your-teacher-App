package com.decagon.rewardyourteacherapi.service;

import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.model.School;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.payload.SearchTeacherResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SearchTeacherService {

    SearchTeacherResponse searchTeacher(String teacher, String name, Long id);
}
