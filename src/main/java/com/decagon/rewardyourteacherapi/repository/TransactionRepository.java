package com.decagon.rewardyourteacherapi.repository;

import com.decagon.rewardyourteacherapi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    List<Transaction> findTransactionBySenderId(Long id);

}

