package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.payload.UserDTO;
import com.decagon.rewardyourteacherapi.service.UserService;
import com.decagon.rewardyourteacherapi.serviceImpl.PaystackTransactionService;
import com.decagon.rewardyourteacherapi.payload.FundingRequestDTO;
import com.decagon.rewardyourteacherapi.util.Responder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.decagon.rewardyourteacherapi.service.WalletService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    UserService userService;

    private WalletService walletService;

    private PaystackTransactionService transaction;

    @PostMapping(value = "")
    public ResponseEntity<APIResponse> editStudentProfile(@RequestBody UserDTO request) {
        return Responder.okay(userService.updateUserProfile(request));
    }

    @PostMapping("/wallet-fund")
    public ResponseEntity<APIResponse> fundWallet(@RequestParam ("amount")BigDecimal amount) {
        return Responder.okay(walletService.fundStudentWallet(amount));
    }

    @PostMapping("/test")
    public ResponseEntity<?> pay(@RequestBody FundingRequestDTO request) throws Exception {
        return ResponseEntity.ok(transaction.initTransaction(request));
    }

    @PostMapping("/test/{reference}")
    public ResponseEntity<?> pay(@PathVariable("reference") String request) throws Exception{
        return ResponseEntity.ok(transaction.verifyTransaction(request));
    }

}
