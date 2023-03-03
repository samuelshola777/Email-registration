package com.Email.registration.Emailregistration.service;

import com.Email.registration.Emailregistration.dto.request.EmailMessageRequest;
import com.Email.registration.Emailregistration.exception.EmailMessageException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EmailMessageServiceTest {
    EmailMessageRequest messageRequest1;
    EmailMessageRequest messageRequest2;
    EmailMessageRequest messageRequest3;
    EmailMessageRequest messageRequest4;
    @Autowired
    private  EmailMessageService messageService;


    @BeforeEach
    void setUp() {
    messageRequest1 = new EmailMessageRequest();
    messageRequest1.setTopic("day two after the election");
    messageRequest1.setSubject("omo guy i'm starting" +
            " to get worried and scared because it's feels" +
            " like tunubu is winnning the election");
    messageRequest1.setReceiverEmail("ami12Sweet.lady@gmail.com");

    messageRequest2 = new EmailMessageRequest();
    messageRequest2.setTopic("i spoke to tomison on the 2/27/2023");
    messageRequest2.setSubject("i can't wait to speak to tomison at  night about the tech field");
    messageRequest2.setReceiverEmail("iyaMaria4@gmail.com");


    messageRequest3 = new  EmailMessageRequest();
    messageRequest3.setTopic("thank God say school dey resume next month ");
    messageRequest3.setSubject("bt i learned we'll be having " +
            "online classes and i'm actually not happy about that");
    messageRequest3.setReceiverEmail("iyaMaria4@gmail.com");


    messageRequest4 = new EmailMessageRequest();
    messageRequest4.setTopic("amirah is  cooking macarony");
    messageRequest4.setSubject("sister aliece gave me some " +
            "packages yesterday and they were lots of stuff");
    messageRequest4.setReceiverEmail("SweetJoy45@gmail.com");
    }

    @Test
    void testThatWeCanWriteAndSaveMessage() throws EmailMessageException {


   messageService.sendEmailMessage(messageRequest1);
    messageService.sendEmailMessage(messageRequest2);
    messageService.sendEmailMessage(messageRequest3);
    messageService.sendEmailMessage(messageRequest4);
    assertEquals(4, messageService.countMessages());

    }
    @Test
    void testThatWeSendMessageToAnotherEmailAccount() throws EmailMessageException {
        messageService.sendEmailMessage(messageRequest1);

    }

    @Test
    void testThatWeCountAvailableMessages() throws EmailMessageException {

        assertEquals("available messages : "+2, messageService.countAvailableMessages("iyaMaria4@gmail.com"));

    }
    @Test
    void testThatUserCanViewAllMessages() throws EmailMessageException {

        assertEquals("user can view all", messageService.viewAllMessages("iyaMaria4@gmail.com",1,1));


    }
    @Test
    void testThatAllMessagesCanBeDelete(){
        messageService.deleteAllMessages();
    }


}