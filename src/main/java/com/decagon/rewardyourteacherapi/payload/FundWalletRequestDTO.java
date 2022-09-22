package com.decagon.rewardyourteacherapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundWalletRequestDTO {
    private String email;
    private BigDecimal amount;
}
