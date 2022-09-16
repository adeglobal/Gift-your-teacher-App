package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.exception.AuthorizationException;
import com.decagon.rewardyourteacherapi.exception.UserAlreadyExistsException;
import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.util.Responder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RewardExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<APIResponse> GenericException(AuthorizationException ex){
        return  Responder.unAuthorized(ex.getMessage());
    }

    public ResponseEntity<APIResponse> TakenEmailException(UserAlreadyExistsException ex){
        return  Responder.alreadyExists(ex.getMessage());
    }
}
