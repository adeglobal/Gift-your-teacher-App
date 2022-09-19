package com.decagon.rewardyourteacherapi.repository;

import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.model.School;
import com.decagon.rewardyourteacherapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchTeacherRepository extends JpaRepository<User, Long> {


    List<User> findUserByRoleAndAndLastNameAndId(Role role, String lastName, Long id);

}
