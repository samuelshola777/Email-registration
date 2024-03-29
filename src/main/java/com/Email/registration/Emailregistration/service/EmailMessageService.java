package com.Email.registration.Emailregistration.service;

import com.Email.registration.Emailregistration.data.model.EmailAddress;
import com.Email.registration.Emailregistration.dto.request.EmailMessageRequest;
import com.Email.registration.Emailregistration.dto.response.EmailMessageResponse;
import com.Email.registration.Emailregistration.exception.EmailMessageException;

import java.util.List;

public interface EmailMessageService {


    EmailAddress writeEmailMessage(EmailMessageRequest messageRequest1);

    long countMessages();

    EmailMessageResponse sendEmailMessage(EmailMessageRequest messageRequest1) throws EmailMessageException;

    String countAvailableMessages(String s) throws EmailMessageException;

    List<EmailAddress> viewAllMessages(String emailAddress, int page, int size) throws EmailMessageException;
//    EmailMessageResponse getAllMessages(String emailAddress, int pageNum, int pageSize);

    void deleteAllMessages();
}
