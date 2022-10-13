package com.decagon.rewardyourteacherapi.serviceImpl;

import com.decagon.rewardyourteacherapi.exception.UserDoesNotExistException;
import com.decagon.rewardyourteacherapi.exception.UserNotFoundException;
import com.decagon.rewardyourteacherapi.mapper.PayloadToModel;
import com.decagon.rewardyourteacherapi.model.Transaction;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.TransactionDTO;
import com.decagon.rewardyourteacherapi.repository.TransactionRepository;
import com.decagon.rewardyourteacherapi.repository.UserRepository;
import com.decagon.rewardyourteacherapi.service.TransactionService;
import com.decagon.rewardyourteacherapi.util.ContextEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<TransactionDTO> transactionHistory() {
        User user = userRepository.findByEmail(ContextEmail.getEmail()).orElseThrow(()->
                new UserNotFoundException(String.format("user with email: %s not found", ContextEmail.getEmail())));
        return transactionRepository.findTransactionsBySender(user).stream().map(PayloadToModel::mapTransactToDTO).collect(Collectors.toList());
    }

    public BigDecimal totalMoneySent(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String sender = ((UserDetails)principal).getUsername();
        BigDecimal moneySent= new BigDecimal(0);
         User user  = userRepository.findByEmail(sender).orElseThrow(()-> new UserDoesNotExistException(String.format("user with email: %s not found", sender)));
         List<Transaction> transactions = transactionRepository.findTransactionsBySender(user);
         for(Transaction transaction: transactions){
             if(transaction.getRecipient().getId() != user.getId()){
                 moneySent = moneySent.add(transaction.getAmount());
             }
         }
         return moneySent;

    }

}
