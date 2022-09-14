package com.decagon.rewardyourteacherapi.service;


import com.decagon.rewardyourteacherapi.model.*;
import com.decagon.rewardyourteacherapi.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotificationServiceImp implements NotificationService {


     final NotificationRepository notificationRepository;
    @Autowired
    public NotificationServiceImp(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
        @Override
    public void  saveTransactionNotification(Transaction transaction){
        String message = "";
        if(transaction.getSender().getId() == transaction.getRecipient().getId()){
            Notification notification = new Notification();
            message = "You have successfully funded you wallet with N"+ transaction.getAmount();
            notification.setUsers(transaction.getSender());
            notification.setMessage(message);
            System.out.println("working a");
            notificationRepository.save(notification);
        }
        else{
            Notification notification = new Notification();
            notification.setMessage("A former student has successfully funded your wallet. Say Hi...");
            notification.setUsers(transaction.getRecipient());
            notificationRepository.save(notification);
            Notification notification2 = new Notification("You've successfully funded your teacher's wallet with N"+ transaction.getAmount(),
                    transaction.getSender());
            notificationRepository.save(notification2);
            Iterable<Notification> not = notificationRepository.findAll();
            System.out.println(not);
        }

    }

    @Override
    public void saveMessageNotification(Message message) {
        Notification notification = new Notification(message.getContent(), message.getReceiver());
        notificationRepository.save(notification);
    }

    public Notification findNotification(String message, Users users){
        return notificationRepository.findByMessageAndUsers(message, users).orElse(null);
    }




}
