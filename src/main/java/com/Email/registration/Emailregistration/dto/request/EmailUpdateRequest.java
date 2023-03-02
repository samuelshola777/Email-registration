package com.Email.registration.Emailregistration.dto.request;

import lombok.Data;

@Data
public class EmailUpdateRequest {
    private String email;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;


}
