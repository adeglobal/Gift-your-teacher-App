package com.decagon.rewardyourteacherapi.serviceImpl;

import com.decagon.rewardyourteacherapi.exception.UserDoesNotExistException;
import com.decagon.rewardyourteacherapi.model.Transaction;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.model.Wallet;
import com.decagon.rewardyourteacherapi.payload.FundWalletRequestDTO;
import com.decagon.rewardyourteacherapi.repository.TransactionRepository;
import com.decagon.rewardyourteacherapi.repository.UserRepository;
import com.decagon.rewardyourteacherapi.repository.WalletRepository;
import com.decagon.rewardyourteacherapi.service.NotificationService;
import com.decagon.rewardyourteacherapi.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {
   private final WalletRepository walletRepository;
   private final TransactionRepository transactionRepository;
   private final NotificationService notificationService;
   private final UserRepository userRepository;


    @Override
    public Wallet fundStudentWallet(FundWalletRequestDTO fundingRequest)  {
        User user = userRepository.findByEmail(fundingRequest.getEmail()).orElseThrow(()->
                new UserDoesNotExistException(String.format("User with email, %s not found", fundingRequest.getEmail())));
        Wallet wallet = walletRepository.findWalletByUserId(user).orElse(null);
        BigDecimal balance = wallet.getTotal();
        balance = balance.add(fundingRequest.getAmount());
        wallet.setTotal(balance);
        notificationService.saveTransactionNotification(transactionRepository.save(new Transaction(user, user, balance)));
        return walletRepository.save(wallet);
    }
}


