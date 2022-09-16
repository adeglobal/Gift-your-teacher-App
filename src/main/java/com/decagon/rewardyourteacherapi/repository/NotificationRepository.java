package com.decagon.rewardyourteacherapi.repository;


import com.decagon.rewardyourteacherapi.model.Notification;
import com.decagon.rewardyourteacherapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Optional<Notification> findByMessageAndUser(String message, User User);
}
