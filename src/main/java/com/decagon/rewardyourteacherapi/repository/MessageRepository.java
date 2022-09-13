package com.decagon.rewardyourteacherapi.repository;

import com.decagon.rewardyourteacherapi.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
