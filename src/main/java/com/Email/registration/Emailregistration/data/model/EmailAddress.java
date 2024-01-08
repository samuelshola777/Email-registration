package com.Email.registration.Emailregistration.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity

public class EmailAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String content;
    private String subject;
    private String receiverEmail;
    private LocalDateTime messageSendingTime;
    @ManyToOne
    private AppUser appUser;



}
