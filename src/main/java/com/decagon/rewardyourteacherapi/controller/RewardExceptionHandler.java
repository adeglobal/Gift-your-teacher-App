package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.exception.AuthorizationException;
import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.util.Responder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RewardExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<APIResponse> GenericException(AuthorizationException ex){
        Responder responder =new Responder();
        return  responder.NotFound(ex.getMessage());
    }
}
