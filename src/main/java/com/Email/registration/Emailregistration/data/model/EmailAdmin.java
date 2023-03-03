package com.Email.registration.Emailregistration.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Builder
@ToString
public class EmailAdmin {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emailId;

    private String userFirstname;

    private String userLastname;

    private String userName;

    private  String password;

    private String phoneNumber;
    private String userEmailAddress;

   @OneToMany(mappedBy = "emailAdmin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailMessage> messageList = new ArrayList<>();

   public void assignEmailMessage(EmailMessage message){
       messageList.add(message);
      message.setEmailAdmin(this);
   }

}