package com.decagon.rewardyourteacherapi.service;


import com.decagon.rewardyourteacherapi.model.Wallet;
import com.decagon.rewardyourteacherapi.payload.FundWalletRequestDTO;

public interface WalletService {
    Wallet fundStudentWallet(FundWalletRequestDTO walletFundingRequestDto);

}
