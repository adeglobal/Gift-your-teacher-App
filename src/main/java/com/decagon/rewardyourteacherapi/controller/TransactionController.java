package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.model.Role;
import com.decagon.rewardyourteacherapi.payload.APIResponse;

import com.decagon.rewardyourteacherapi.payload.UserRegistrationDTO;
import com.decagon.rewardyourteacherapi.service.TransactionService;
import com.decagon.rewardyourteacherapi.util.Responder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
public class TransactionController {

    private TransactionService transactionService;
    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping("/transaction-history/{id}")
    public ResponseEntity<APIResponse> getTransactionHistory(@PathVariable Long id){

        return Responder.okay(transactionService.transactionHistory(id));
    }
}
