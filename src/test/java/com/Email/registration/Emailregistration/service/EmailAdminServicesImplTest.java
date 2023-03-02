package com.Email.registration.Emailregistration.service;

import com.Email.registration.Emailregistration.dto.request.EmailAdminRequest;
import com.Email.registration.Emailregistration.dto.request.EmailLoginRequest;
import com.Email.registration.Emailregistration.dto.request.EmailUpdateRequest;
import com.Email.registration.Emailregistration.exception.EmailException;
import com.Email.registration.Emailregistration.exception.EmailMessageException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.security.auth.login.LoginException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EmailAdminServicesImplTest {
    @Autowired
    private EmailAdminService emailAdminService;
    EmailAdminRequest emailAdmin;
    EmailAdminRequest emailAdmin1;
    EmailAdminRequest emailAdmin2;
    EmailAdminRequest emailAdmin3;
    EmailAdminRequest emailAdmin4;
    EmailAdminRequest emailAdmin5;
    EmailLoginRequest loginRequest;
    EmailUpdateRequest updateRequest;

    @BeforeEach
    @Test
    void setUp() {
        updateRequest = new EmailUpdateRequest();
        updateRequest.setFirstName("black");
        updateRequest.setLastName("gabriel");
        updateRequest.setPassword("egg");
        updateRequest.setPhoneNumber("08123456789");
        updateRequest.setEmail("iyaMaria4@gmail.com");


        loginRequest = new EmailLoginRequest();
        loginRequest.setEmail("sweetJoy45@gmail.com");
        loginRequest.setPassword("tori");


        emailAdmin = new EmailAdminRequest();
        emailAdmin.setUserFirstname("DonGi");
        emailAdmin.setUserLastname("sam");
        emailAdmin.setUserName("Sambone007");
        emailAdmin.setPassword("blueBoat");
        emailAdmin.setPhoneNumber("09099332737");

        emailAdmin1 = new EmailAdminRequest();
        emailAdmin1.setUserFirstname("semi-colon");
        emailAdmin1.setUserLastname("joy");
        emailAdmin1.setUserName("SweetJoy45");
        emailAdmin1.setPassword("tori");
        emailAdmin1.setPhoneNumber("07099332737");
        emailAdmin1.setUserEmailAddress("ami12Sweet.lady@gmail.com");

        emailAdmin2 = new EmailAdminRequest();
        emailAdmin2.setUserFirstname("Adegunwa");
        emailAdmin2.setUserLastname("Adewunmi");
        emailAdmin2.setUserName("iyaLaWoSpringoot8");
        emailAdmin2.setPassword("breadNBeans");
        emailAdmin2.setPhoneNumber("07034567824");

        emailAdmin3 = new EmailAdminRequest();
        emailAdmin3.setUserFirstname("yetunde");
        emailAdmin3.setUserLastname("benefacino");
        emailAdmin3.setUserName("iyaMaria4");
        emailAdmin3.setPassword("egg");
        emailAdmin3.setPhoneNumber("09062666877");
        emailAdmin3.setUserEmailAddress("iyaMaria4@gmail.com");

        emailAdmin4 = new EmailAdminRequest();
        emailAdmin4.setUserFirstname("samuel");
        emailAdmin4.setUserLastname("amaka");
        emailAdmin4.setUserName("ami12Sweet.lady");
        emailAdmin4.setPassword("goat456");
        emailAdmin4.setPhoneNumber("09099332737");

     emailAdmin5 = new EmailAdminRequest();
        emailAdmin5.setUserFirstname("femi");
        emailAdmin5.setUserLastname("amaka");
        emailAdmin5.setUserName("amiGeniusBaby56");
        emailAdmin5.setPassword("goat");
        emailAdmin5.setPhoneNumber("09099283433");
    }
@Disabled
    @Test
    void testThatUserCanRegisterForEmail() throws EmailException {
        emailAdminService.registerEmailAccount(emailAdmin1);
        emailAdminService.registerEmailAccount(emailAdmin);
        emailAdminService.registerEmailAccount(emailAdmin2);
        emailAdminService.registerEmailAccount(emailAdmin3);
        emailAdminService.registerEmailAccount(emailAdmin4);
        emailAdminService.registerEmailAccount(emailAdmin5);
    assertEquals(6,emailAdminService.countEmailUsers());
    }
    @Test
    void testThatUserCanBeFindById() throws EmailException {

        assertEquals("iyaMaria4",emailAdminService.findById(4).getUserName());
    }


    @Test
    void testThatWeCanVerifyIfPhoneNumberContainAlphabetic() throws EmailException {
        assertEquals("07099332737", emailAdminService.
                verifyIfPhoneNumberContainAlphabetic
                        (emailAdminService.mapFromRequestToEmailAdmin(emailAdmin1))
                .getPhoneNumber());
    }

    @Test
    void verifyTheLengthOfPhoneNumber(){
        assertEquals(11, emailAdminService.verifyLengthOfPhoneNumber(emailAdmin1.getPhoneNumber()));
    }

@Test
    void creatingEmailGenerator(){

assertEquals("SweetJoy45@gmail.com", emailAdminService.createEmailGenerator(emailAdminService.mapFromRequestToEmailAdmin(emailAdmin1)));
}
@Test
    void testThatWeCanCountTheNumberOfEmailUser(){
        assertEquals(6, emailAdminService.countEmailUsers());
}
@Disabled
@Test
    void testWeCanDeleteAllEmailUsers(){
        emailAdminService.deleteAllEmailUsers();
        assertEquals(0, emailAdminService.countEmailUsers());

}

    @Test
    void testThatUsersCanLoginToThereEmailAccount() throws LoginException {

        assertEquals("07099332737", emailAdminService.loginToThereEmailAccount("tori","sweetJoy45@gmail.com").getPhoneNumber());
    }
    @Test
    void  testThatUserCanChangeFirstName() throws LoginException {

       assertEquals("black", emailAdminService.changeEmailUserFirstName(updateRequest));

    }
    @Test
    void  testThatUserCanChangeLastName() throws LoginException {

       assertEquals("gabriel", emailAdminService.changeEmailUserLastName(updateRequest));

    }
    @Test
    void testThatUserCanBeFountWithEmailAddress() throws EmailMessageException {

        assertEquals("amaka", emailAdminService.findByEmailAddress(emailAdmin1.getUserEmailAddress()).getUserLastname());

    }


}

