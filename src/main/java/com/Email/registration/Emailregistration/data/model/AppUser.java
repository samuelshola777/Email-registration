package com.Email.registration.Emailregistration.data.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Builder
@ToString
public class AppUser {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emailId;

    private String userFirstname;

    private String userLastname;

    private String userName;

    private  String password;

    private String phoneNumber;
    private String userEmailAddress;



}