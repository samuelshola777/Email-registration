package com.Email.registration.Emailregistration.controller;

import com.Email.registration.Emailregistration.data.model.EmailAdmin;
import com.Email.registration.Emailregistration.data.model.EmailMessage;
import com.Email.registration.Emailregistration.dto.request.EmailMessageRequest;
import com.Email.registration.Emailregistration.dto.response.EmailMessageResponse;
import com.Email.registration.Emailregistration.exception.EmailMessageException;
import com.Email.registration.Emailregistration.service.EmailAdminService;
import com.Email.registration.Emailregistration.service.EmailMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmailMessageController {
    @Autowired
    private EmailMessageService emailService;



@PostMapping("/sendEmail")
    public ResponseEntity<EmailMessageResponse> sendEmailMessage(@RequestBody EmailMessageRequest messageRequest) {
    EmailMessageResponse emailMessage = emailService.sendEmailMessage(messageRequest);
    return new ResponseEntity<>(emailMessage,HttpStatus.OK);
}

@GetMapping("/getAllEmailMessages/{emailAddress}/{pagNum}/{page_size}")
    public ResponseEntity<List<EmailMessage>> getAllEmailMessages(@PathVariable("emailAddress")
  String emailAddress, @PathVariable("pagNum") int pagNum, @PathVariable("page_size") int pagesize){
    return new ResponseEntity<>(emailService.viewAllMessages(emailAddress, pagNum, pagesize),HttpStatus.OK);
}


    }




