package com.Email.registration.Emailregistration.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailAdminRequest {
    private long emailId;
    private String userFirstname;
    private String userLastname;
    private String userName;
    private String password;
    private String phoneNumber;
    private String userEmailAddress;

}
