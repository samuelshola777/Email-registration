package com.Email.registration.Emailregistration.service;
import com.Email.registration.Emailregistration.data.model.EmailAdmin;
import com.Email.registration.Emailregistration.data.model.EmailMessage;
import com.Email.registration.Emailregistration.data.repository.EmailAdminRepository;
import com.Email.registration.Emailregistration.data.repository.EmailMessageRepository;
import com.Email.registration.Emailregistration.dto.request.EmailAdminRequest;
import com.Email.registration.Emailregistration.dto.request.EmailMessageRequest;
import com.Email.registration.Emailregistration.dto.request.EmailUpdateRequest;
import com.Email.registration.Emailregistration.dto.response.EmailAdminResponse;
import com.Email.registration.Emailregistration.exception.EmailException;
import com.Email.registration.Emailregistration.exception.EmailMessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.List;

@Service
public class EmailAdminServicesImpl implements EmailAdminService {
    private EmailAdminRepository emailAdminRepository;

    @Autowired
    public EmailAdminServicesImpl(EmailAdminRepository emailAdminRepository) {
        this.emailAdminRepository = emailAdminRepository;
    }
    @Override
    public String registerEmailAccount(EmailAdminRequest emailAdminRequest) throws EmailException {
        EmailAdmin emailAdminObject = mapFromRequestToEmailAdmin(emailAdminRequest);
        if (!verifyIfUserNameContainsDigit(emailAdminObject.getUserName())) throw new EmailException("user name should contain at least one digit");
        emailAdminObject  = checkIfUserNameExistInDatabase(emailAdminObject);
        emailAdminObject = verifyIfPhoneNumberContainAlphabetic(emailAdminObject);
        String generatedEmailAddress = createEmailGenerator(emailAdminObject);
        emailAdminObject.setUserEmailAddress(generatedEmailAddress);
        emailAdminRepository.save(emailAdminObject);
        return generatedEmailAddress;
    }
    @Override
    public EmailAdmin findById(long id) throws EmailException {
        return emailAdminRepository.findById(id).orElseThrow(
        ()-> new EmailException(String.format("could not find email with id %d", id)));

    }

    public EmailAdmin checkIfUserNameExistInDatabase(EmailAdmin emailAdmin) throws EmailException {
        if(emailAdminRepository.existsByUserName(emailAdmin.getUserName()))
            throw new EmailException("user name already, please enter a new name username".toUpperCase());

        return emailAdmin;
    }
    public EmailAdmin mapFromRequestToEmailAdmin(EmailAdminRequest emailAdminRequest) {
        return EmailAdmin.builder().
                emailId(emailAdminRequest.getEmailId()).
                userFirstname(emailAdminRequest.getUserFirstname())
                .userLastname(emailAdminRequest.getUserLastname())
                .userName(emailAdminRequest.getUserName())
                .password(emailAdminRequest.getPassword())
                .phoneNumber(emailAdminRequest.getPhoneNumber()).build();
    }
    public boolean ifContainAlphabet(String number) {
        char[] alphabets = {
                'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'c', 'C', 'f', 'F', 'g', 'G', 'h',
                'H', 'i', 'I', 'j', 'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P',
                'q', 'Q', 'r', 'R', 's', 'S',
                't', 'T', 'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z'};

        for (int i = 0; i < number.length(); i++) {
            for (int j = 0; j < alphabets.length; j++) {
                if (number.charAt(i) == alphabets[j]) {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public EmailAdmin verifyIfPhoneNumberContainAlphabetic(EmailAdmin emailAdmin1) throws EmailException {
        if (ifContainAlphabet(emailAdmin1.getPhoneNumber())) {
            throw new EmailException("you have an invalid phone number");
        }
        if (verifyLengthOfPhoneNumber(emailAdmin1.getPhoneNumber()) != 11) {
            throw new EmailException("you have a mobile number with the length "
                    + emailAdmin1.getPhoneNumber().length() + "  \n which is not valid ");
        }
        return emailAdmin1;
    }
    @Override
    public int verifyLengthOfPhoneNumber(String phoneNumber) {
        return phoneNumber.length();
    }
    @Override
    public boolean verifyIfUserNameContainsDigit(String userName) {
        for (int i = 0; i < userName.length(); i++) {
            if (Character.isDigit(userName.charAt(i))) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String createEmailGenerator(EmailAdmin emailAdmin1) {
        String emailEnd = "@gmail.com";
        emailAdmin1.setUserEmailAddress(emailAdmin1.getUserName()+emailEnd);
         return emailAdmin1.getUserEmailAddress();
    }
    @Override
    public long countEmailUsers() {
        return emailAdminRepository.count();
    }
    @Override
    public String deleteAllEmailUsers() {
        emailAdminRepository.deleteAll();
        return "All email deleted successfully";
    }

    @Override
    public void saveEmailAdmin(EmailAdmin emailAdmin) {
        emailAdminRepository.save(emailAdmin);
    }

    @Override
    public long viewUserId(EmailAdminRequest emailAdmin3) {
        EmailAdmin emailAdmin = mapFromRequestToEmailAdmin(emailAdmin3);

        return emailAdmin.getEmailId();
    }
    @Override
    public EmailAdmin loginToThereEmailAccount(String password, String emailAddress) throws LoginException {
     EmailAdmin incomingEmail =  emailAdminRepository.
    findByUserEmailAddress(emailAddress);
     if (incomingEmail == null) {
         throw new LoginException("invalid email address".toUpperCase());
     } else if (! incomingEmail.getPassword().equals(password)) {
         throw new LoginException("incorrect password".toUpperCase());
     }
     return incomingEmail;
    }

//    public EmailAdmin recieveEmail(EmailMessageRequest emailMessageRequest) throws EmailMessageException {
//   EmailAdmin receiverEmailAccount = emailAdminRepository.findByUserEmailAddress(emailMessageRequest.getReceiverEmail());
//        if (receiverEmailAccount == null) {
//    throw new EmailMessageException("cant find an existing email Account with the email address "+emailMessageRequest.getReceiverEmail());
//        }
//        receiverEmailAccount.getMessageList().add(emailMessageRequest);
//    return receiverEmailAccount;}
    @Override
    public String changeEmailUserFirstName(EmailUpdateRequest updateRequest) throws LoginException {
    EmailAdmin emailAdmin = loginToThereEmailAccount(updateRequest.getPassword(), updateRequest.getEmail());
    emailAdmin.setUserFirstname(updateRequest.getFirstName());
    emailAdminRepository.save(emailAdmin);
        return emailAdmin.getUserFirstname();
    }
    @Override
    public String changeEmailUserLastName(EmailUpdateRequest updateRequest) throws LoginException {
    EmailAdmin emailAdmin = loginToThereEmailAccount(updateRequest.getPassword(), updateRequest.getEmail());
    emailAdmin.setUserLastname(updateRequest.getLastName());
    emailAdminRepository.save(emailAdmin);
        return String.format(emailAdmin.getUserLastname());
    }

    @Override
    public EmailAdmin findByEmailAddress(String emailAddress) throws EmailMessageException {
 EmailAdmin foundEmail = emailAdminRepository.findByUserEmailAddress(emailAddress);
 if (foundEmail == null) throw new EmailMessageException("Could not find");
        return foundEmail;
    }


}