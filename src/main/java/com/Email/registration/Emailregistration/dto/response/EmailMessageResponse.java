package com.Email.registration.Emailregistration.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EmailMessageResponse {
    private String topic;
    private String subject;
    private String senderEmail;
    private String receiverEmail;
    private LocalTime messageSendingTime;
    private LocalDate messageSendingDate;


}
