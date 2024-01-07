package com.Email.registration.Emailregistration.data.repository;

import com.Email.registration.Emailregistration.data.model.EmailMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailMessageRepository extends JpaRepository<EmailMessage, Long> {


    long countAllByReceiverEmail(String emailAddress);
    Page<EmailMessage> findAllByReceiverEmail(String emailAddress, Pageable pageable);


    EmailMessage findByReceiverId(long receiverId);
}
