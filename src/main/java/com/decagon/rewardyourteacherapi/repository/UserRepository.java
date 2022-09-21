package com.decagon.rewardyourteacherapi.repository;

import com.decagon.rewardyourteacherapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findUsersByRoleAndAndFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            Role role, String name, String name2);

    Page<User> findAllBySchoolAndRole(School school, Role role, Pageable pageable);

}
