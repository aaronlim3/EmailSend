package com.limac.emailsend.service;

import com.limac.emailsend.model.EmailBody;

public interface EmailService {
    void send(EmailBody email);
}
