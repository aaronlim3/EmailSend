package com.limac.emailsend.exception;

public class EmptyEmailData extends RuntimeException {
    public EmptyEmailData(String message) {
        super(message);
    }
}
