package com.decagon.rewardyourteacherapi.security;

import com.decagon.rewardyourteacherapi.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ApplicationServiceRepository extends CrudRepository<User, Long> {
    public Optional<User> selectUserByEmailAndPassword(String email);
}
