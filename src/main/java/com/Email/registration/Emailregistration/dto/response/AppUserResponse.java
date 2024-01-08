package com.Email.registration.Emailregistration.dto.response;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AppUserResponse {
    private String message;

    public AppUserResponse(String message) {
        this.message = message;
    }

    private String userFirstname;

    private String userLastname;

    private String userName;

    private  String password;

    private String phoneNumber;

}
