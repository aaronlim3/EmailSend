package com.limac.emailsend.validator;

import com.limac.emailsend.exception.EmptyEmailBody;
import com.limac.emailsend.exception.EmptyEmailData;
import com.limac.emailsend.exception.EmptyEmailRecipient;
import com.limac.emailsend.exception.InvalidEmailRecipient;
import com.limac.emailsend.exception.WrongEmailRecipientFormat;
import com.limac.emailsend.model.EmailBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Value;

import static org.mockito.ArgumentMatchers.anyString;

class RequestValidatorTest {

    @InjectMocks
    private RequestValidator requestValidator;

    @BeforeEach
    public void setup() {
        requestValidator = new RequestValidator();
    }

    @Test
    void validateEmailBody_emptyEmailBody() {
        EmptyEmailBody thrown = Assertions
                .assertThrows(EmptyEmailBody.class, () -> {
                    requestValidator.validateEmailBody(null);
                }, anyString());

        Assertions.assertEquals("Please check email request is empty.", thrown.getMessage());
    }

    @Test
    void validateEmailBody_BlankFields() {
        EmptyEmailData thrown = Assertions
                .assertThrows(EmptyEmailData.class, () -> {
                    requestValidator.validateEmailBody(new EmailBody("","",""));
                }, anyString());

        Assertions.assertEquals("Missing data for email recipient, message and subject.", thrown.getMessage());
    }

    @Test
    void validateEmailBody_emailRecipientIsBlank() {
        EmptyEmailRecipient thrown = Assertions
                .assertThrows(EmptyEmailRecipient.class, () -> {
                    requestValidator.validateEmailBody(new EmailBody("","subject","message"));
                }, anyString());

        Assertions.assertEquals("Please add a valid email recipient address.", thrown.getMessage());
    }

}