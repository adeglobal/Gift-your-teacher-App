package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.exception.AuthorizationException;
import com.decagon.rewardyourteacherapi.exception.UserAlreadyExistsException;
import com.decagon.rewardyourteacherapi.exception.UserNotFoundException;
import com.decagon.rewardyourteacherapi.exception.UserDoesNotExistException;
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

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<APIResponse> UserAlreadyExists(UserAlreadyExistsException ex){
        return  Responder.alreadyExists(ex.getMessage());
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<APIResponse> UserNotFound (UserNotFoundException ex){
        return  Responder.notFound (ex.getMessage());
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<APIResponse> UserDoesNotExist(UserDoesNotExistException ex){
        return  Responder.doesNotExists(ex.getMessage());
    }
}
