package com.Email.registration.Emailregistration.service;
import com.Email.registration.Emailregistration.data.model.AppUser;
import com.Email.registration.Emailregistration.data.repository.AppUserRepository;
import com.Email.registration.Emailregistration.dto.request.EmailAdminRequest;
import com.Email.registration.Emailregistration.dto.request.EmailLoginRequest;
import com.Email.registration.Emailregistration.dto.request.EmailUpdateRequest;
import com.Email.registration.Emailregistration.dto.response.AppUserResponse;
import com.Email.registration.Emailregistration.exception.EmailException;
import com.Email.registration.Emailregistration.exception.EmailMessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public class AppUserServicesImpl implements AppUserService {
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserServicesImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUserResponse registerEmailAccount(EmailAdminRequest emailAdminRequest) throws EmailException {
        AppUser appUserObject = mapFromRequestToEmailAdmin(emailAdminRequest);
        if (!verifyIfUserNameContainsDigit(appUserObject.getUserName()))
            throw new EmailException("user name should contain at least one digit");
        verifyIfPhoneNumberContainAlphabetic(appUserObject);
      AppUser savedAppUser =  appUserRepository.save(appUserObject);
      return   AppUserResponse.builder()
              .userFirstname(savedAppUser.getFirstName()).
              userLastname(savedAppUser.getLastName()).
              userName(savedAppUser.getUserName()).build();
    }

    @Override
    public AppUser findById(long id) throws EmailException {
        return appUserRepository.findById(id).orElseThrow(
                () -> new EmailException(String.format("could not find email with id %d", id)));
    }


 AppUser mapFromRequestToEmailAdmin(EmailAdminRequest emailAdminRequest) {
        if (appUserRepository.existsByUserName(emailAdminRequest.getUserName())) {
            throw new EmailException("user name already exist, please enter a new name username".toUpperCase());
        }
        if (emailAdminRequest.getUserName() == null){
            emailAdminRequest.setUserName(emailAdminRequest.getUserFirstname()+" "+emailAdminRequest.getUserLastname());
        }
        return AppUser.builder().
                firstName(emailAdminRequest.getUserFirstname())
                .lastName(emailAdminRequest.getUserLastname())
                .userName(emailAdminRequest.getUserName())
                .email(emailAdminRequest.getUserEmailAddress())
                .password(emailAdminRequest.getPassword())
                .phoneNumber(emailAdminRequest.getPhoneNumber()).build();
    }

   void ifContainAlphabet(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (Character.isAlphabetic(number.charAt(i))) throw new EmailException("Invalid input: " + number);
        }
    }


  void verifyIfPhoneNumberContainAlphabetic(AppUser appUser1) throws EmailException {
        ifContainAlphabet(appUser1.getPhoneNumber());
        if (appUser1.getPhoneNumber().length() != 11) {
            throw new EmailException("you have a mobile number with the length "
                    + appUser1.getPhoneNumber().length() + "  \n which is not valid ");
        }
    }


    boolean verifyIfUserNameContainsDigit(String userName) {
        for (int i = 0; i < userName.length(); i++) {
            if (Character.isDigit(userName.charAt(i))) {
                return true;
            }
        }
        return false;
    }



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
//        AppUser incomingEmail =  appUserRepository.
//                findByUserEmailAddress(loginRequest.getEmail());
//        if (incomingEmail == null) {
//            throw new LoginException("invalid email address".toUpperCase());
//        } else if (! incomingEmail.getPassword().equals(loginRequest.getPassword())) {
//            throw new LoginException("incorrect password".toUpperCase());
//        }
        return new AppUser();
    }


    @Override
    public String changeEmailUserFirstName(EmailUpdateRequest updateRequest) throws LoginException {
        EmailLoginRequest loginRequest = new EmailLoginRequest();
        loginRequest.setEmail(updateRequest.getEmail());
        loginRequest.setPassword(updateRequest.getPassword());
        AppUser appUser = loginToEmailAccount(loginRequest);
        appUser.setFirstName(updateRequest.getFirstName());
        appUserRepository.save(appUser);
        return "first name has been changed, first name is now: ".toUpperCase() + appUser.getFirstName();
    }

    @Override
    public String changeEmailUserLastName(EmailUpdateRequest updateRequest) throws LoginException {
        EmailLoginRequest loginRequest = new EmailLoginRequest();
        loginRequest.setEmail(updateRequest.getEmail());
        loginRequest.setPassword(updateRequest.getPassword());
        AppUser appUser = loginToEmailAccount(loginRequest);
        appUser.setLastName(updateRequest.getLastName());
        appUserRepository.save(appUser);
        return String.format("last name has been changed, last name is now  ".toUpperCase() + appUser.getLastName());
    }

    @Override
    public AppUser findByEmailAddress(String emailAddress) throws EmailMessageException {
//        AppUser foundEmail = appUserRepository.findByUserEmailAddress(emailAddress);
//        if (foundEmail == null) throw new EmailMessageException("invalid email address");
//        return foundEmail;
        return new AppUser();
    }


}

//}