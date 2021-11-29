package com.limac.emailsend.service;

import com.limac.emailsend.exception.ConnectionTimeout;
import com.limac.emailsend.model.EmailBody;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Log logger = LogFactory.getLog(EmailServiceImpl.class);

    @Value("${default.email.sender}")
    private String DEFAULT_EMAIL_SENDER;

    private JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void send(EmailBody email) {

        logger.debug("simple mail message...");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(DEFAULT_EMAIL_SENDER);
        mailMessage.setTo(email.getEmailRecipient());
        mailMessage.setSubject(email.getEmailSubject());
        mailMessage.setText(email.getEmailMessage());

        logger.debug("sending message...");
        javaMailSender.send(mailMessage);
        logger.debug("error connection to mail server");

    }
}