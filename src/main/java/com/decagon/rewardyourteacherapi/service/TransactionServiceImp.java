package com.decagon.rewardyourteacherapi.service;

import com.decagon.rewardyourteacherapi.model.Transaction;
import com.decagon.rewardyourteacherapi.repository.TransactionRepository;
import org.springframework.stereotype.Service;


@Service
public class TransactionServiceImp implements TransactionService {


    private TransactionRepository transactionRepository;

    public TransactionServiceImp(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void TransactionNotification(Transaction transaction){
        transaction.setChecked(false);
        transactionRepository.save(transaction);
    }

}
