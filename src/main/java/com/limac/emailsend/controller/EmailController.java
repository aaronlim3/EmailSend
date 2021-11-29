package com.limac.emailsend.controller;

import com.limac.emailsend.model.EmailBody;
import com.limac.emailsend.service.EmailService;
import com.limac.emailsend.validator.RequestValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= "/api")
public class EmailController {

    private static final Log logger = LogFactory.getLog(EmailController.class);

    @Autowired
    private EmailService emailService;

    @Autowired
    private RequestValidator requestValidator;


    @PostMapping("/send")
    public void send(@RequestBody EmailBody emailbody) {
        logger.debug("send method");
        requestValidator.validateEmailBody(emailbody);
        logger.debug("request validation completed");
        emailService.send(emailbody);
        logger.debug("service request completed");
    }
}
