package com.Email.registration.Emailregistration.service;

import com.Email.registration.Emailregistration.data.model.AppUser;
import com.Email.registration.Emailregistration.dto.request.EmailAdminRequest;
import com.Email.registration.Emailregistration.dto.request.EmailLoginRequest;
import com.Email.registration.Emailregistration.dto.request.EmailUpdateRequest;
import com.Email.registration.Emailregistration.dto.response.AppUserResponse;
import com.Email.registration.Emailregistration.exception.EmailException;
import com.Email.registration.Emailregistration.exception.EmailMessageException;

import javax.security.auth.login.LoginException;


public interface AppUserService {
    AppUserResponse registerEmailAccount(EmailAdminRequest emailAdmin1) throws EmailException;

    AppUser findById(long id) throws EmailException;

    long countEmailUsers();

    String deleteAllEmailUsers();
    void saveEmailAdmin(AppUser appUser);



    AppUser loginToEmailAccount(EmailLoginRequest loginRequest) throws LoginException;

    String changeEmailUserFirstName(EmailUpdateRequest updateRequest) throws LoginException;

    String changeEmailUserLastName(EmailUpdateRequest updateRequest) throws LoginException;

    AppUser findByEmailAddress(String emailAddress) throws EmailMessageException;


}
