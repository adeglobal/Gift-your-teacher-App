package com.decagon.rewardyourteacherapi.service;


import com.decagon.rewardyourteacherapi.model.Wallet;
import com.decagon.rewardyourteacherapi.payload.FundingRequestDTO;

public interface WalletService {
    Wallet fundStudentWallet(FundingRequestDTO walletFundingRequestDto);

}
