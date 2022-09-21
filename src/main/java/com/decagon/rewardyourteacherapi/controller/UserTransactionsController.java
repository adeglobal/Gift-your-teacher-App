package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.service.UserService;
import com.decagon.rewardyourteacherapi.util.Responder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserTransactionsController {
    private  UserService userService;

    public UserTransactionsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/wallet-balance/{id}")
    public ResponseEntity<APIResponse> getWalletAmount (@PathVariable Long id){
        return Responder.okay(userService.getCurrentWalletBalance(id));
    }
}
