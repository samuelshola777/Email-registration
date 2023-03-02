package com.Email.registration.Emailregistration.service;

import com.Email.registration.Emailregistration.data.model.EmailAdmin;
import com.Email.registration.Emailregistration.data.model.EmailMessage;
import com.Email.registration.Emailregistration.data.repository.EmailMessageRepository;
import com.Email.registration.Emailregistration.dto.request.EmailMessageRequest;
import com.Email.registration.Emailregistration.exception.EmailMessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;


@Service
public class EmailMessageServiceImpl implements  EmailMessageService{
    @Autowired
    private EmailMessageRepository messageRepository;
    @Autowired
    private EmailAdminService adminService;



//
//@Autowired
//    public EmailMessageServiceImpl(EmailMessageRepository messageRepository) {
//        this.messageRepository = messageRepository;
//    }

    @Override
    public EmailMessage writeEmailMessage(EmailMessageRequest messageRequest1) {
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTopic(messageRequest1.getTopic());
        emailMessage.setSubject(messageRequest1.getSubject());
        return emailMessage;
    }

    @Override
    public long countMessages() {
        return messageRepository.count();
    }

    @Override
    public EmailMessage sendEmailMessage(EmailMessageRequest messageRequest1) throws EmailMessageException {
    EmailMessage writtenEmailMessage = writeEmailMessage(messageRequest1);
    EmailAdmin messageReceiver = adminService.findByEmailAddress(messageRequest1.getReceiverEmail());
    writtenEmailMessage.setReceiverEmail(messageReceiver.getUserEmailAddress());
    writtenEmailMessage.setSenderEmail(writtenEmailMessage.getSenderEmail());
    writtenEmailMessage.setEmailAdmin_Id(messageReceiver.getEmailId());
//    messageReceiver.getMessageList().add(writtenEmailMessage);
   adminService.saveEmailAdmin(messageReceiver);
    messageRepository.save(writtenEmailMessage);
        return writtenEmailMessage;
    }

    @Override
    public String countAvailableMessages(String emailAddress)  {
        long countMessage = messageRepository.countAllByReceiverEmail(emailAddress);
        if (countMessage <= 0) return " no available messages ".toUpperCase();
        return "available messages : "+countMessage;
    }

    @Override
    public List<EmailMessage> viewAllMessages(String emailAddress, int pageNum, int pageSize) throws EmailMessageException {
        var pageable = PageRequest.of(pageNum, pageSize);
        Page<EmailMessage> mailsList =  messageRepository.findAllByReceiverEmail(emailAddress, pageable);
        System.out.println(mailsList);
        if (!mailsList.hasContent()) throw new EmailMessageException("no existing messages");
        return mailsList.getContent();
    }
}
