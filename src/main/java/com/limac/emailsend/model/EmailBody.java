package com.limac.emailsend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailBody {

    @JsonProperty("email_recipient")
    private String emailRecipient;

    @JsonProperty("email_subject")
    private String emailSubject;

    @JsonProperty("email_message")
    private String emailMessage;

    public EmailBody(String emailTo, String subject, String message) {
        this.emailRecipient = emailTo;
        this.emailSubject = subject;
        this.emailMessage = message;
    }

    public EmailBody() {
    }

    public String getEmailRecipient() {
        return emailRecipient;
    }

    public void setEmailRecipient(String emailRecipient) {
        this.emailRecipient = emailRecipient;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailMessage() {
        return emailMessage;
    }

    public void setEmailMessage(String emailMessage) {
        this.emailMessage = emailMessage;
    }
}
