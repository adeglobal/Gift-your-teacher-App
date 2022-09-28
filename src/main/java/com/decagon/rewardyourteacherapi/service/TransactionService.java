package com.decagon.rewardyourteacherapi.service;

import com.decagon.rewardyourteacherapi.model.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> transactionHistory();
}
