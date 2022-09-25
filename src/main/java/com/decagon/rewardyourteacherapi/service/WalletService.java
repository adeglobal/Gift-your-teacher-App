package com.decagon.rewardyourteacherapi.service;



import com.decagon.rewardyourteacherapi.payload.FundingRequestDTO;
import com.decagon.rewardyourteacherapi.payload.UserDTO;

import java.math.BigDecimal;

public interface WalletService {
    UserDTO fundStudentWallet(BigDecimal amount);

}
