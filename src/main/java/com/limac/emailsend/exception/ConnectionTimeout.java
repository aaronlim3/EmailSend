package com.limac.emailsend.exception;

public class ConnectionTimeout extends RuntimeException {
    public ConnectionTimeout(String message) {
        super(message);
    }
}
