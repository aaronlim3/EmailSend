package com.limac.emailsend.exception;

public class EmptyEmailRecipient extends RuntimeException {
    public EmptyEmailRecipient(String message) {
        super(message);
    }
}
