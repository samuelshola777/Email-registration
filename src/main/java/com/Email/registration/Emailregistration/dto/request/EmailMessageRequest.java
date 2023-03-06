package com.Email.registration.Emailregistration.dto.request;

import com.Email.registration.Emailregistration.data.model.EmailMessage;
import lombok.Data;

@Data
public class EmailMessageRequest{

    private String topic;

    private String subject;

    private String senderEmail;
    private String receiverEmail;
    private long emailAdminId;


}
