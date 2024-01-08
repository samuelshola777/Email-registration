package com.Email.registration.Emailregistration.data.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Builder
public class AppUser {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private  String password;
    private String phoneNumber;
    private String email;



}