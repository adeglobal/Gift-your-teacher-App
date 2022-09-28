package com.decagon.rewardyourteacherapi.serviceImpl;

import com.decagon.rewardyourteacherapi.exception.UserDoesNotExistException;
import com.decagon.rewardyourteacherapi.mapper.PayloadToModel;
import com.decagon.rewardyourteacherapi.model.Transaction;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.UserDTO;
import com.decagon.rewardyourteacherapi.repository.TransactionRepository;
import com.decagon.rewardyourteacherapi.repository.UserRepository;
import com.decagon.rewardyourteacherapi.service.NotificationService;
import com.decagon.rewardyourteacherapi.service.WalletService;
import com.decagon.rewardyourteacherapi.util.ContextEmail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {
   private final TransactionRepository transactionRepository;
   private final NotificationService notificationService;
   private final UserRepository userRepository;


    @Override
    public UserDTO fundStudentWallet(BigDecimal amount)  {
        User user = userRepository.findByEmail(ContextEmail.getEmail()).orElseThrow(()->
                new UserDoesNotExistException(String.format("User with email, %s not found", ContextEmail.getEmail())));
        BigDecimal balance = user.getWallet();
        balance = balance.add(amount);
        user.setWallet(balance);
        Transaction ts = new Transaction(user, user, amount);
        ts =transactionRepository.save(ts);
        notificationService.saveTransactionNotification(ts);
        return PayloadToModel.mapUserToDTO(userRepository.save(user));
    }
}


