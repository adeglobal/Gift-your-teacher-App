package com.decagon.rewardyourteacherapi.service;

import com.decagon.rewardyourteacherapi.model.Message;
import com.decagon.rewardyourteacherapi.model.Notification;
import com.decagon.rewardyourteacherapi.model.Transaction;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class NotificationServiceImp implements NotificationService {


    private NotificationRepository notificationRepository;
    @Autowired
    public NotificationServiceImp(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void  TransactionNotification(Transaction transaction){
        String message = "";
        if(transaction.getSender().getId() == transaction.getRecipient().getId()){
            Notification notification = new Notification();
            message = "You have successfully funded you wallet with N"+ transaction.getAmount();
            notification.setUser(transaction.getSender());
            notification.setMessage(message);
            notification.setTransaction_id(transaction.getId());
            notificationRepository.save(notification);
        }
        else{
            ArrayList<Notification> list = new ArrayList<>();
            Notification notification = new Notification();
            notification.setMessage("A former student has successfully funded your wallet. Say Hi...");
            notification.setTransaction_id(transaction.getId());
            notification.setUser(transaction.getRecipient());
            list.add(notification);
            notification = new Notification("You've successfully fundeed your teacher's wallet with N"+ transaction.getAmount(),
                    new User(transaction.getSender().getId()));
            notification.setTransaction_id(transaction.getId());
            list.add(notification);
            notificationRepository.saveAll(list);
        }

    }

    @Override
    public void MessageNotification(Message message) {
        Notification notification = new Notification(message.getContent(), message.getReceiver());
        notificationRepository.save(notification);
    }

}
