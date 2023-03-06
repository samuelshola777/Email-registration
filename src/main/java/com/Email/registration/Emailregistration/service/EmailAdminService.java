package com.Email.registration.Emailregistration.service;

import com.Email.registration.Emailregistration.data.model.EmailAdmin;
import com.Email.registration.Emailregistration.data.model.EmailMessage;
import com.Email.registration.Emailregistration.dto.request.EmailAdminRequest;
import com.Email.registration.Emailregistration.dto.request.EmailUpdateRequest;
import com.Email.registration.Emailregistration.exception.EmailException;
import com.Email.registration.Emailregistration.exception.EmailMessageException;

import javax.security.auth.login.LoginException;


public interface EmailAdminService {
    String registerEmailAccount(EmailAdminRequest emailAdmin1) throws EmailException;



    EmailAdmin findById(long id) throws EmailException;

    EmailAdmin mapFromRequestToEmailAdmin(EmailAdminRequest emailAdminRequest);
    EmailAdmin checkIfUserNameExistInDatabase(EmailAdmin emailAdmin) throws EmailException;
    boolean ifContainAlphabet(String number);

    EmailAdmin verifyIfPhoneNumberContainAlphabetic(EmailAdmin emailAdmin1) throws EmailException;


    int verifyLengthOfPhoneNumber(String phoneNumber);

   boolean verifyIfUserNameContainsDigit(String userName);

    String createEmailGenerator(EmailAdmin emailAdmin1);

    long countEmailUsers();

    String deleteAllEmailUsers();
    void saveEmailAdmin(EmailAdmin emailAdmin);



    EmailAdmin loginToEmailAccount(String password, String emailAddress) throws LoginException;

    String changeEmailUserFirstName(EmailUpdateRequest updateRequest) throws LoginException;

    String changeEmailUserLastName(EmailUpdateRequest updateRequest) throws LoginException;

    EmailAdmin findByEmailAddress(String emailAddress) throws EmailMessageException;

}
