package com.decagon.rewardyourteacherapi.service;


import com.decagon.rewardyourteacherapi.model.Message;
import com.decagon.rewardyourteacherapi.model.Transaction;
import com.decagon.rewardyourteacherapi.model.User;
import org.springframework.mail.SimpleMailMessage;


public interface NotificationService {
     void  saveTransactionNotification(Transaction transaction);
      void saveMessageNotification(Message message);

      SimpleMailMessage SendEmail(User recipient, String message, String subject);
}
