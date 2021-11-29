package com.limac.emailsend.exception;

public class InvalidEmailRecipient extends RuntimeException {
    public InvalidEmailRecipient(String message) {
        super(message);
    }
}
