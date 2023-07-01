package com.Email.registration.Emailregistration.controller;

import com.Email.registration.Emailregistration.data.model.EmailAdmin;
import com.Email.registration.Emailregistration.data.model.EmailMessage;
import com.Email.registration.Emailregistration.dto.request.EmailAdminRequest;
import com.Email.registration.Emailregistration.dto.request.EmailLoginRequest;
import com.Email.registration.Emailregistration.dto.request.EmailUpdateRequest;
import com.Email.registration.Emailregistration.exception.EmailException;
import com.Email.registration.Emailregistration.exception.EmailMessageException;
import com.Email.registration.Emailregistration.service.EmailAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;


@RequestMapping("/api")
@RestController
public class EmailController {
    @Autowired
    EmailAdminService emailService;

    @PostMapping("/register")
    public ResponseEntity<String> createEmailAddress(@RequestBody EmailAdminRequest emailAdminRequest)
            throws EmailException {
    return new ResponseEntity<>(emailService.registerEmailAccount(emailAdminRequest), HttpStatus.CREATED );
    }

    @GetMapping("/findById{id}")
    public ResponseEntity<EmailAdmin> findById(@PathVariable long id) throws EmailException {
        return new ResponseEntity<>(emailService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<EmailAdmin> login(@RequestBody EmailLoginRequest loginRequest) throws EmailException, LoginException {
        return new ResponseEntity<>(emailService.loginToEmailAccount(loginRequest), HttpStatus.CREATED);
    }

    @PostMapping("/change/lastName")
    public ResponseEntity<String> changeLastName(@RequestBody EmailUpdateRequest message) throws LoginException {
        return new  ResponseEntity<>(emailService.changeEmailUserLastName(message), HttpStatus.ACCEPTED);
    }

    @PostMapping("/change/firstName")
    public ResponseEntity<String> changeFirstName(@RequestBody EmailUpdateRequest message) throws LoginException {
        return new  ResponseEntity<>(emailService.changeEmailUserFirstName(message), HttpStatus.ACCEPTED);
    }

    @GetMapping("/findByEmailAddress/{emailAddress}")
    public ResponseEntity<EmailAdmin> findByEmailAddress(@PathVariable String emailAddress) throws EmailMessageException {
        return new ResponseEntity<>(emailService.findByEmailAddress(emailAddress), HttpStatus.OK);
    }
}