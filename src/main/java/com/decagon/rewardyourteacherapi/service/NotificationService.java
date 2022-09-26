package com.decagon.rewardyourteacherapi.service;


import com.decagon.rewardyourteacherapi.model.Message;
import com.decagon.rewardyourteacherapi.model.Notification;
import com.decagon.rewardyourteacherapi.model.Transaction;
import com.decagon.rewardyourteacherapi.model.User;
import org.springframework.data.domain.Page;

import java.util.List;


public interface NotificationService {
     void  saveTransactionNotification(Transaction transaction);
      void saveMessageNotification(Message message);

    Notification findNotification(String message, User user);

    List<Notification> retrieveUserNotification(Long id);
}
