package com.Email.registration.Emailregistration.service;

import com.Email.registration.Emailregistration.data.model.EmailMessage;
import com.Email.registration.Emailregistration.dto.request.EmailMessageRequest;
import com.Email.registration.Emailregistration.exception.EmailMessageException;

public interface EmailMessageService {


    EmailMessage writeEmailMessage(EmailMessageRequest messageRequest1);

    long countMessages();

   EmailMessage sendEmailMessage(EmailMessageRequest messageRequest1) throws EmailMessageException;
}
