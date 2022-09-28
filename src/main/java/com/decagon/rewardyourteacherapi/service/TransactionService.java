package com.decagon.rewardyourteacherapi.service;

import com.decagon.rewardyourteacherapi.model.Transaction;
import com.decagon.rewardyourteacherapi.payload.TransactionDTO;

import java.util.List;

public interface TransactionService {

    List<TransactionDTO> transactionHistory();
}
