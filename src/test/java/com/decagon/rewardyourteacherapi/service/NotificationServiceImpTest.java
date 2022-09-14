package com.decagon.rewardyourteacherapi.service;

import com.decagon.rewardyourteacherapi.RewardYourTeacherApiApplication;
import com.decagon.rewardyourteacherapi.model.Message;
import com.decagon.rewardyourteacherapi.model.Notification;
import com.decagon.rewardyourteacherapi.model.Transaction;
import com.decagon.rewardyourteacherapi.model.Users;
import com.decagon.rewardyourteacherapi.repository.NotificationRepository;
import com.decagon.rewardyourteacherapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RewardYourTeacherApiApplication.class)

class NotificationServiceImpTest {
    Users users1 = new Users( 1L,"bukky", "terroist", "test@gamil.com", "password");
    Users users2 = new Users( 2L,"george", "victim", "test2@gamil.com", "password");
    String  teacherFunded = "A former student has successfully funded your wallet. Say Hi...";
    String  fundedTeacher = "You've successfully funded your teacher's wallet with N";
    String fundedWallet = "You have successfully funded you wallet with N";

    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    UserRepository userRepository;


    NotificationServiceImp notService;


    @Test
    void saveTransactionNotification() {
        Transaction transaction1 = new Transaction(1L, users1, users2, new BigDecimal(12000.21));
        Transaction transaction2 = new Transaction(2L, users1, users1, new BigDecimal(5000));
        NotificationServiceImp notService = new NotificationServiceImp(notificationRepository);
        notService.saveTransactionNotification(transaction1);
        assertNotNull(notificationRepository.findByMessageAndUsers(teacherFunded, users2));
        Notification  not = notificationRepository.findByMessageAndUsers(teacherFunded, users2).orElse(null);
        assertEquals(teacherFunded, not.getMessage());
        assertNotNull(notificationRepository.findByMessageAndUsers(fundedTeacher+transaction1.getAmount(), users1).orElse(null));
        Notification not2 = notificationRepository.findByMessageAndUsers(fundedTeacher+transaction1.getAmount(), users1).orElse(null);
        assertTrue(not2.getMessage().contains(fundedTeacher));
        notService.saveTransactionNotification(transaction2);
        assertNotNull(notificationRepository.findByMessageAndUsers(fundedWallet+transaction2.getAmount(), users1));
        not = notificationRepository.findByMessageAndUsers(fundedWallet+transaction2.getAmount(), users1).orElse(null);
        assertTrue(not.getMessage().contains(fundedWallet));
    }

    @Test
    void saveMessageNotification() {
        userRepository.save(users1);
        userRepository.save(users2);
        Message message = new Message(users1, users2, "Hello governor");
        NotificationServiceImp notService = new NotificationServiceImp(notificationRepository);
        notService.saveMessageNotification(message);
        assertNotNull(notificationRepository.findByMessageAndUsers("Hello governor", users2 ));
    }
}
