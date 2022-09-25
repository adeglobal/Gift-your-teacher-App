package com.decagon.rewardyourteacherapi.serviceImpl;


import com.decagon.rewardyourteacherapi.model.*;
import com.decagon.rewardyourteacherapi.repository.NotificationRepository;
import com.decagon.rewardyourteacherapi.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;


@Service
public class NotificationServiceImp implements NotificationService {

    final NotificationRepository notificationRepository;

    JavaMailSender javaMailSender = new JavaMailSenderImpl();
    @Autowired
    public NotificationServiceImp(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
        @Override
    public void  saveTransactionNotification(Transaction transaction){
        String message = "";
            Notification notification = new Notification();
            if(transaction.getSender().getId() == transaction.getRecipient().getId()){
                message = "You have successfully funded you wallet with N"+ transaction.getAmount();
            notification.setUser(transaction.getSender());
            notification.setMessage(message);
            notificationRepository.save(notification);
            SendEmail(transaction.getRecipient(), message, "Funded your wallet");
        }
        else{
            notification.setMessage("A former student has successfully funded your wallet. Say Hi...");
            notification.setUser(transaction.getRecipient());
            notificationRepository.save(notification);
            Notification notification2 = new Notification("You've successfully funded your teacher's wallet with N"
                    + transaction.getAmount(),
                    transaction.getSender());
            notificationRepository.save(notification2);
            SendEmail(transaction.getRecipient(), message, "You've been rewarded");
            }

    }

    @Override
    public void saveMessageNotification(Message message) {
        Notification notification = new Notification(message.getContent(), message.getReceiver());
        notificationRepository.save(notification);
        SendEmail(message.getReceiver(), message.getContent(), "You've got mail from"+message.getSender().getFirstName());
    }

    public Notification findNotification(String message, User user){
        return notificationRepository.findByMessageAndUser(message, user).orElse(null);
    }

    public SimpleMailMessage SendEmail(User recipient, String message, String subject) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("olamiretj@gmail.com");
        mailMessage.setTo(recipient.getEmail());
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
        return  mailMessage;
    }




}
