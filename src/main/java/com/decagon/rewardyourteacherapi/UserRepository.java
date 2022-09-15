package com.decagon.rewardyourteacherapi;

import com.decagon.rewardyourteacherapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.annotation.Resource;

public interface UserRepository extends JpaRepository<User,Long> {
    @Modifying
    @Query("UPDATE User u SET u.authType = ?2 WHERE u.username = ?1")
    public void updateAuthenticationType(String username, Resource.AuthenticationType authType);
}
