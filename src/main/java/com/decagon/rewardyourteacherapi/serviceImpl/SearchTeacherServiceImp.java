package com.decagon.rewardyourteacherapi.serviceImpl;

import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.model.School;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.payload.SearchTeacherResponse;
import com.decagon.rewardyourteacherapi.repository.SearchTeacherRepository;
import com.decagon.rewardyourteacherapi.repository.UserRepository;
import com.decagon.rewardyourteacherapi.service.SearchTeacherService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class SearchTeacherServiceImp implements SearchTeacherService {
    private SearchTeacherRepository searchTeacherRepository;
    @Autowired
    public SearchTeacherServiceImp(SearchTeacherRepository searchTeacherRepository) {
        this.searchTeacherRepository = searchTeacherRepository;
    }

    public SearchTeacherResponse searchTeacher(Role role, String name, Long id){
        List<User> userList = searchTeacherRepository.findUserByRoleAndAndLastNameAndId(role,name,id);
        return new SearchTeacherResponse("Success", LocalDateTime.now(),userList);

    }
}
