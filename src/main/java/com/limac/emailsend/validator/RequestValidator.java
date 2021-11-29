package com.limac.emailsend.validator;

import com.limac.emailsend.exception.EmptyEmailBody;
import com.limac.emailsend.exception.EmptyEmailData;
import com.limac.emailsend.exception.EmptyEmailRecipient;
import com.limac.emailsend.exception.InvalidEmailRecipient;
import com.limac.emailsend.exception.WrongEmailRecipientFormat;
import com.limac.emailsend.model.EmailBody;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RequestValidator {
    private static final Log logger = LogFactory.getLog(RequestValidator.class);

    @Value("${email.valid.pattern}")
    private String VALID_EMAIL_REGEX;

    public void validateEmailBody(EmailBody emailbody) {
        if (null == emailbody) {
            logger.debug("Please check email request is empty.");
            throw new EmptyEmailBody("Please check email request is empty.");
        }
        if (StringUtils.isBlank(emailbody.getEmailRecipient())
                && StringUtils.isBlank(emailbody.getEmailSubject())
                && StringUtils.isBlank(emailbody.getEmailMessage())) {
            logger.debug("Missing data for email recipient, message and subject.");
            throw new EmptyEmailData("Missing data for email recipient, message and subject.");
        }
        validateEmailRecipientIsBlank(emailbody.getEmailRecipient());
    }
    private void validateEmailRecipientIsBlank(String emailRecipient) {
        logger.debug("validate email_recipient if blank/empty");
        if (StringUtils.isBlank(emailRecipient)) {
            logger.debug("Please add a valid email recipient address.");
            throw new EmptyEmailRecipient("Please add a valid email recipient address.");
        } else {
            validateEmailFormat(emailRecipient);
        }
    }

    private void validateEmailFormat(String emailRecipient) {
        logger.debug("validate email_recipient format");
        if (!Pattern.compile(VALID_EMAIL_REGEX).matcher(emailRecipient).matches()) {
            logger.debug("Email recipient address format is incorrect.");
            throw new WrongEmailRecipientFormat("Email recipient address format is incorrect.");
        }
    }

    public RequestValidator() {
    }
}
