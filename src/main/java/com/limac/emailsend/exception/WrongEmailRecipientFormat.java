package com.limac.emailsend.exception;

public class WrongEmailRecipientFormat extends RuntimeException {
    public WrongEmailRecipientFormat(String message) {
        super(message);
    }
}
