package com.decagon.rewardyourteacherapi.service;


import com.decagon.rewardyourteacherapi.model.Message;
import com.decagon.rewardyourteacherapi.model.Transaction;


public interface NotificationService {
     void  saveTransactionNotification(Transaction transaction);
      void saveMessageNotification(Message message);

}
