package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.util.InitializeTransactionRequest;
import com.decagon.rewardyourteacherapi.serviceImpl.PaystackTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayStackController {
    @Autowired
    private PaystackTransactionService transaction;
    @PostMapping("/test")
    public ResponseEntity<?> pay(@RequestBody InitializeTransactionRequest request) throws Exception {
        return ResponseEntity.ok(transaction.initTransaction(request));
    }
}
