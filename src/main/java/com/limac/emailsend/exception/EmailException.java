package com.limac.emailsend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailException {
    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime timeStamp;
}
