package com.Email.registration.Emailregistration.data.repository;

import com.Email.registration.Emailregistration.data.model.EmailAdmin;
import com.Email.registration.Emailregistration.data.model.EmailMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailMessageRepository extends JpaRepository<EmailMessage, Long> {
//    EmailMessage findAllByEmailAdmin(EmailAdmin emailAdmin);

}
