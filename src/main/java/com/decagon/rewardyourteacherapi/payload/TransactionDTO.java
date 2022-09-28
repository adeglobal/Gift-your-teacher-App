package com.decagon.rewardyourteacherapi.payload;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@AllArgsConstructor
public class TransactionDTO {

    private UserDTO sender;
    private UserDTO recipient;
    private BigDecimal amount;
    private LocalDateTime transactionDate;


}
