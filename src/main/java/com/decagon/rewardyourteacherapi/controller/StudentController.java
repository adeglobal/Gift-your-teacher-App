package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.payload.FundWalletRequestDTO;
import com.decagon.rewardyourteacherapi.service.WalletService;
import com.decagon.rewardyourteacherapi.util.Responder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
        @Autowired
        private WalletService walletService;

        @PostMapping("/wallet/fund")
        public ResponseEntity<APIResponse> fundWallet(@RequestBody FundWalletRequestDTO fundWalletRequestDto) throws Exception {
            return Responder.okay(walletService.fundStudentWallet(fundWalletRequestDto));
        }

}
