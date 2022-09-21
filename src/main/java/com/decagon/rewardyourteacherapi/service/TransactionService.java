package com.decagon.rewardyourteacherapi.service;

import com.decagon.rewardyourteacherapi.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

//    Transaction createTransaction(TransactionDTO transactionDTO);
    List<Transaction> transactionHistory(Long id);
}
