package com.limac.emailsend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class EmailExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        EmailException emailException = new EmailException(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(emailException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailRecipient.class)
    public ResponseEntity<Object> handleEmailRequestException(InvalidEmailRecipient ex) {
        EmailException emailException = new EmailException(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(emailException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyEmailBody.class)
    public ResponseEntity<Object> handleEmailRequestException(EmptyEmailBody ex) {
        EmailException emailException = new EmailException(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(emailException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConnectionTimeout.class)
    public ResponseEntity<Object> handleConnectionTimeout(ConnectionTimeout ex) {
        EmailException emailException = new EmailException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        return new ResponseEntity<>(emailException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new EmailException(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
