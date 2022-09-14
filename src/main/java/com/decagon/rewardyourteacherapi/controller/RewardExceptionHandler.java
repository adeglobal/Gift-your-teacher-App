package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.exception.ApplicationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RewardExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public String GenericException(ApplicationException ex){
        return ex.getMessage();
    }
}
