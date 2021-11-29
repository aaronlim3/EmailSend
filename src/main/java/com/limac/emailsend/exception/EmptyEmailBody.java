package com.limac.emailsend.exception;

public class EmptyEmailBody extends RuntimeException {
    public EmptyEmailBody(String message) {
        super(message);
    }
}
