package com.limac.emailsend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.limac.emailsend.exception.InvalidEmailRecipient;
import com.limac.emailsend.model.EmailBody;
import com.limac.emailsend.service.EmailService;
import com.limac.emailsend.validator.RequestValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EmailControllerTest {

    @Mock
    private EmailService emailService;

    @Mock
    private RequestValidator requestValidator;

    private EmailBody emailBody;

    @InjectMocks
    private EmailController emailController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(emailController).build();
        emailBody = new EmailBody("someone@example.com","subject","message");
    }

    @AfterEach
    void tearDown() {
        emailBody = null;
    }

    @Test
    void send() throws Exception {

        doNothing().when(emailService).send(emailBody);
        doNothing().when(requestValidator).validateEmailBody(emailBody);

        String json = asJsonString(emailBody);

        mockMvc.perform(MockMvcRequestBuilders.
                post("/api/send").
                contentType(MediaType.APPLICATION_JSON).
                content(json)
                ).andExpect(status().isOk());
        verify(emailService,times(1)).send(any(EmailBody.class));
        verify(requestValidator,times(1)).validateEmailBody(any(EmailBody.class));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}