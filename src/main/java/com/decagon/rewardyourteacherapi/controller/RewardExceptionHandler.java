package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.exception.ApplicationException;
import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.util.Responder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RewardExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<APIResponse> GenericException(ApplicationException ex){
        Responder responder =new Responder();
        return  responder.NotFound(ex.getMessage());
    }
}
