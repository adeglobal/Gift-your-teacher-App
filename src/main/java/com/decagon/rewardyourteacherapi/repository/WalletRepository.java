package com.decagon.rewardyourteacherapi.repository;

import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
    Optional<Wallet> findWalletById(long id);
}
