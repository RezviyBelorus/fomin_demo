package com.example.demo.controller;

import com.example.demo.exception.AlreadyExistException;
import com.example.demo.exception.IllegalRequestException;
import com.example.demo.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException() {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity handleUserAlreadyExistException() {
        return new ResponseEntity (HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleUserNotFoundException() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalRequestException.class)
    public ResponseEntity handleIllegalRequestException() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
