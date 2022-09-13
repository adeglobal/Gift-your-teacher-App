package com.decagon.rewardyourteacherapi.repository;

import com.decagon.rewardyourteacherapi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}
