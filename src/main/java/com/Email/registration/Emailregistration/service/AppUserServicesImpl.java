package com.Email.registration.Emailregistration.service;
import com.Email.registration.Emailregistration.data.model.AppUser;
import com.Email.registration.Emailregistration.data.repository.AppUserRepository;
import com.Email.registration.Emailregistration.dto.request.EmailAdminRequest;
import com.Email.registration.Emailregistration.dto.request.EmailLoginRequest;
import com.Email.registration.Emailregistration.dto.request.EmailUpdateRequest;
import com.Email.registration.Emailregistration.exception.EmailException;
import com.Email.registration.Emailregistration.exception.EmailMessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public class AppUserServicesImpl implements AppUserService {
    private AppUserRepository appUserRepository;
    @Autowired
    public AppUserServicesImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }
    @Override
    public String registerEmailAccount(EmailAdminRequest emailAdminRequest) throws EmailException {
        AppUser appUserObject = mapFromRequestToEmailAdmin(emailAdminRequest);
        String username = emailAdminRequest.getUserName();
        if (username == null) throw new EmailException("user name is null");
        if (!verifyIfUserNameContainsDigit(username)) throw new EmailException("user name should contain at least one digit");
        appUserObject = checkIfUserNameExistInDatabase(appUserObject);
        appUserObject = verifyIfPhoneNumberContainAlphabetic(appUserObject);
        String generatedEmailAddress = createEmailGenerator(appUserObject);
        appUserObject.setUserEmailAddress(generatedEmailAddress);
        appUserRepository.save(appUserObject);
        return generatedEmailAddress;
    }
    @Override
    public AppUser findById(long id) throws EmailException {
        return appUserRepository.findById(id).orElseThrow(
        ()-> new EmailException(String.format("could not find email with id %d", id)));

    }

    public AppUser checkIfUserNameExistInDatabase(AppUser appUser) throws EmailException {
        if(appUserRepository.existsByUserName(appUser.getUserName()))
            throw new EmailException("user name already, please enter a new name username".toUpperCase());

        return appUser;
    }
    public AppUser mapFromRequestToEmailAdmin(EmailAdminRequest emailAdminRequest) {
        return AppUser.builder().
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
    public AppUser verifyIfPhoneNumberContainAlphabetic(AppUser appUser1) throws EmailException {
        if (ifContainAlphabet(appUser1.getPhoneNumber())) {
            throw new EmailException("you have an invalid phone number");
        }
        if (verifyLengthOfPhoneNumber(appUser1.getPhoneNumber()) != 11) {
            throw new EmailException("you have a mobile number with the length "
                    + appUser1.getPhoneNumber().length() + "  \n which is not valid ");
        }
        return appUser1;
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
    public String createEmailGenerator(AppUser appUser1) {
        String emailEnd = "@gmail.com";
        appUser1.setUserEmailAddress(appUser1.getUserName()+emailEnd);
         return appUser1.getUserEmailAddress();
    }
    @Override
    public long countEmailUsers() {
        return appUserRepository.count();
    }
    @Override
    public String deleteAllEmailUsers() {
        appUserRepository.deleteAll();
        return "All email deleted successfully";
    }

    @Override
    public void saveEmailAdmin(AppUser appUser) {
        appUserRepository.save(appUser);
    }



    @Override
    public AppUser loginToEmailAccount(EmailLoginRequest loginRequest) throws LoginException {
        AppUser incomingEmail =  appUserRepository.
                findByUserEmailAddress(loginRequest.getEmail());
        if (incomingEmail == null) {
            throw new LoginException("invalid email address".toUpperCase());
        } else if (! incomingEmail.getPassword().equals(loginRequest.getPassword())) {
            throw new LoginException("incorrect password".toUpperCase());
        }
        return incomingEmail;
    }


    @Override
    public String changeEmailUserFirstName(EmailUpdateRequest updateRequest) throws LoginException {
        EmailLoginRequest loginRequest = new EmailLoginRequest();
        loginRequest.setEmail(updateRequest.getEmail());
        loginRequest.setPassword(updateRequest.getPassword());
    AppUser appUser = loginToEmailAccount(loginRequest);
    appUser.setUserFirstname(updateRequest.getFirstName());
    appUserRepository.save(appUser);
        return "first name has been changed, first name is now: ".toUpperCase()+ appUser.getUserFirstname();
    }
    @Override
    public String changeEmailUserLastName(EmailUpdateRequest updateRequest) throws LoginException {
        EmailLoginRequest loginRequest = new EmailLoginRequest();
        loginRequest.setEmail(updateRequest.getEmail());
        loginRequest.setPassword(updateRequest.getPassword());
        AppUser appUser = loginToEmailAccount(loginRequest);
        appUser.setUserLastname(updateRequest.getLastName());
    appUserRepository.save(appUser);
        return String.format("last name has been changed, last name is now  ".toUpperCase()+ appUser.getUserLastname());
    }

    @Override
    public AppUser findByEmailAddress(String emailAddress) throws EmailMessageException {
        AppUser foundEmail = appUserRepository.findByUserEmailAddress(emailAddress);
        if (foundEmail == null) throw new EmailMessageException("invalid email address");
        return foundEmail;
    }

    @Override
    public String printOut() {
       return "boneshaker";
    }


}