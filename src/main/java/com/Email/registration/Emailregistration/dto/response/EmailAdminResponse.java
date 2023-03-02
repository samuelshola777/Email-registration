package com.Email.registration.Emailregistration.dto.response;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmailAdminResponse {
    private String message;

    public EmailAdminResponse(String message) {
        this.message = message;
    }
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

}
