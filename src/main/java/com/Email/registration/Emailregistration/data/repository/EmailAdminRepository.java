package com.Email.registration.Emailregistration.data.repository;

import com.Email.registration.Emailregistration.data.model.EmailAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailAdminRepository extends JpaRepository<EmailAdmin,Long> {


    boolean existsByUserName(String userName);



    EmailAdmin findByUserEmailAddress(String userEmailAddress);
Optional<EmailAdmin> findEmailAdminByUserEmailAddress(String userEmailAddress);

}
