package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.payload.APIResponse;

import com.decagon.rewardyourteacherapi.service.TransactionService;
import com.decagon.rewardyourteacherapi.service.UserService;
import com.decagon.rewardyourteacherapi.util.Responder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
public class TransactionController {

    private final TransactionService transactionService;
    private final UserService userService;
    @Autowired
    public TransactionController(TransactionService transactionService, UserService userService) {
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @GetMapping("/transaction-history")
    public ResponseEntity<APIResponse> getTransactionHistory(){
        return Responder.okay(transactionService.transactionHistory());
    }

    @GetMapping("/wallet-balance")
    public ResponseEntity<APIResponse> getWalletAmount (){
        return Responder.okay(userService.getCurrentWalletBalance());
    }
}
