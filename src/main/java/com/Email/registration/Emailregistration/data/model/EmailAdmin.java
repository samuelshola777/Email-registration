package com.Email.registration.Emailregistration.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Builder
@ToString
public class EmailAdmin {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emailId;
    @NonNull
    private String userFirstname;
    @NonNull
    private String userLastname;
    @NonNull
    private String userName;
    @NonNull
    private  String password;
    @NonNull
    private String phoneNumber;
    private String userEmailAddress;
//
//   @OneToMany(mappedBy = "emailAdmin", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<EmailMessage> messageList = new ArrayList<>();

}