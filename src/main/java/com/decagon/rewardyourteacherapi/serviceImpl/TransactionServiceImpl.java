package com.decagon.rewardyourteacherapi.serviceImpl;

import com.decagon.rewardyourteacherapi.exception.UserNotFoundException;
import com.decagon.rewardyourteacherapi.model.Transaction;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.repository.TransactionRepository;
import com.decagon.rewardyourteacherapi.repository.UserRepository;
import com.decagon.rewardyourteacherapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;


    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Transaction> transactionHistory() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)principal).getUsername();
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException(String.format("user with email: %s not found", email )));
        return transactionRepository.findTransactionsBySender(user);
    }

}
