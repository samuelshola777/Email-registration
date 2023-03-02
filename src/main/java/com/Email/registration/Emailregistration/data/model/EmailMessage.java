package com.Email.registration.Emailregistration.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private String senderEmail;
    private String receiverEmail;

    private LocalTime messageSendingTime = LocalTime.now();
    private LocalDate  messageSendingDate = LocalDate.now();

//    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emailAdmin_id")
    private Long emailAdmin_Id;
//    private EmailAdmin emailAdmin ;

    public String toString() {
        return String.format("""
        
        message sending date :    %s \n
        message sending time :     %s \n 
        message sender emailAddress :  %s \n
        
        message topic : \n        
            %s \n    
        subject : \n
            %s        
                
                
            
                """, messageSendingDate, messageSendingTime, senderEmail, (topic == null ?  topic = " ":topic ),subject);
    }

}
