package com.Email.registration.Emailregistration.data.repository;

import com.Email.registration.Emailregistration.data.model.EmailAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailMessageRepository extends JpaRepository<EmailAddress, Long> {


    long countAllByReceiverEmail(String emailAddress);
    Page<EmailAddress> findAllByReceiverEmail(String emailAddress, Pageable pageable);



}
