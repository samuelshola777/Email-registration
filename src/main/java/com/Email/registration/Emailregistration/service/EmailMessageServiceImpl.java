package com.Email.registration.Emailregistration.service;

import com.Email.registration.Emailregistration.data.model.AppUser;
import com.Email.registration.Emailregistration.data.model.EmailAddress;
import com.Email.registration.Emailregistration.data.repository.EmailMessageRepository;
import com.Email.registration.Emailregistration.dto.request.EmailMessageRequest;
import com.Email.registration.Emailregistration.dto.response.EmailMessageResponse;
import com.Email.registration.Emailregistration.exception.EmailMessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmailMessageServiceImpl implements  EmailMessageService{
    @Autowired
    private EmailMessageRepository messageRepository;
    @Autowired
    private AppUserService adminService;



//
//@Autowired
//    public EmailMessageServiceImpl(EmailMessageRepository messageRepository) {
//        this.messageRepository = messageRepository;
//    }

    @Override
    public EmailAddress writeEmailMessage(EmailMessageRequest messageRequest1) {
        AppUser foundAppUser = adminService.findByEmailAddress(messageRequest1.getSenderEmail());
        if (foundAppUser == null) throw new RuntimeException("app user does not exist");
        EmailAddress emailAddress = new EmailAddress();
        emailAddress.setContent(messageRequest1.getTopic());
        emailAddress.setSubject(messageRequest1.getSubject());
        emailAddress.setAppUser(foundAppUser);
        return emailAddress;
    }


    @Override
    public long countMessages() {
        return messageRepository.count();
    }

    @Override
    public EmailMessageResponse sendEmailMessage(EmailMessageRequest messageRequest1) throws EmailMessageException {
    EmailAddress writtenEmailAddress = writeEmailMessage(messageRequest1);
    AppUser messageReceiver = adminService.findByEmailAddress(messageRequest1.getReceiverEmail());
//    writtenEmailAddress.setSenderEmail(writtenEmailAddress.getSenderEmail());
//    writtenEmailAddress.setReceiverId(messageReceiver.getId());
    writtenEmailAddress.setAppUser(messageReceiver);
    messageRepository.save(writtenEmailAddress);
//    messageReceiver.assignEmailMessage(writtenEmailMessage);
    adminService.saveEmailAdmin(messageReceiver);
    return mapToResponse(writtenEmailAddress);
    }
    public EmailMessageResponse mapToResponse(EmailAddress emailAddress){
        EmailMessageResponse mail = new EmailMessageResponse();
        mail.setTopic(emailAddress.getContent());
        mail.setSubject(emailAddress.getSubject());
//        mail.setSenderEmail(emailAddress.getSenderEmail());
//        mail.setReceiverEmail(emailAddress.getReceiverEmail());
//        mail.setMessageSendingTime(emailAddress.getMessageSendingTime());
//        mail.setMessageSendingDate(emailAddress.getMessageSendingDate());
        return mail;
    }

    @Override
    public String countAvailableMessages(String emailAddress)  {
        long countMessage = messageRepository.countAllByReceiverEmail(emailAddress);
        if (countMessage <= 0) return " no available messages ".toUpperCase();
        return "available messages : "+countMessage;
    }

    @Override
    public List<EmailAddress> viewAllMessages(String emailAddress, int pageNum, int pageSize) throws EmailMessageException {
        var pageable = PageRequest.of(pageNum, pageSize);
        Page<EmailAddress> mailsList =  messageRepository.findAllByReceiverEmail(emailAddress, pageable);
        if (!mailsList.hasContent()) throw new EmailMessageException("no existing messages");
        return mailsList.getContent();
    }
//    public EmailMessageResponse getAllMessages(String emailAddress, int pageNum, int pageSize) throws EmailMessageException {
//        var pageable = PageRequest.of(pageNum, pageSize);
//        Page<EmailMessage> mailsList =  messageRepository.findAllByReceiverEmail(emailAddress, pageable);
//        if (!mailsList.hasContent()) throw new EmailMessageException("no existing messages");
//        List<EmailMessage> messageList    = mailsList.getContent();
//     return  mapToResponse((EmailMessage) messageList;
//    }

    @Override
    public void deleteAllMessages() {
        messageRepository.deleteAll();
    }

//    public EmailMessageResponse mapFromListToResponse(List<EmailMessage> emailMessagelist){
//        EmailMessageResponse mail = new EmailMessageResponse();
//        mail.setTopic(emailMessage.getTopic());
//        mail.setSubject(emailMessage.getSubject());
//        mail.setSenderEmail(emailMessage.getSenderEmail());
//        mail.setReceiverEmail(emailMessage.getReceiverEmail());
//        mail.setMessageSendingTime(emailMessage.getMessageSendingTime());
//        mail.setMessageSendingDate(emailMessagelist.get().getMessageSendingDate());
//
//        return mail;
//    }
}
