package com.Email.registration.Emailregistration.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity

public class EmailMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long emailId;

    private String topic;

    private String subject;
//   @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "emailAdmin_id")
   private long receiverId;
    private String senderEmail;
    private String receiverEmail;

    private LocalTime messageSendingTime = LocalTime.now();
    private LocalDate  messageSendingDate = LocalDate.now();

    @ManyToOne
    private EmailAdmin emailAdmin ;



}
