package com.Email.registration.Emailregistration.service;

import com.Email.registration.Emailregistration.data.model.AppUser;
import com.Email.registration.Emailregistration.dto.request.EmailAdminRequest;
import com.Email.registration.Emailregistration.dto.request.EmailLoginRequest;
import com.Email.registration.Emailregistration.dto.request.EmailUpdateRequest;
import com.Email.registration.Emailregistration.exception.EmailException;
import com.Email.registration.Emailregistration.exception.EmailMessageException;

import javax.security.auth.login.LoginException;


public interface AppUserService {
    String registerEmailAccount(EmailAdminRequest emailAdmin1) throws EmailException;



    AppUser findById(long id) throws EmailException;

    AppUser mapFromRequestToEmailAdmin(EmailAdminRequest emailAdminRequest);
    AppUser checkIfUserNameExistInDatabase(AppUser appUser) throws EmailException;
    boolean ifContainAlphabet(String number);

    AppUser verifyIfPhoneNumberContainAlphabetic(AppUser appUser1) throws EmailException;


    int verifyLengthOfPhoneNumber(String phoneNumber);

   boolean verifyIfUserNameContainsDigit(String userName);

    String createEmailGenerator(AppUser appUser1);

    long countEmailUsers();

    String deleteAllEmailUsers();
    void saveEmailAdmin(AppUser appUser);



    AppUser loginToEmailAccount(EmailLoginRequest loginRequest) throws LoginException;

    String changeEmailUserFirstName(EmailUpdateRequest updateRequest) throws LoginException;

    String changeEmailUserLastName(EmailUpdateRequest updateRequest) throws LoginException;

    AppUser findByEmailAddress(String emailAddress) throws EmailMessageException;

    String printOut();
}
