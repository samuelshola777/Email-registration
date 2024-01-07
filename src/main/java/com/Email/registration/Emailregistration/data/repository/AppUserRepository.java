package com.Email.registration.Emailregistration.data.repository;

import com.Email.registration.Emailregistration.data.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {


    boolean existsByUserName(String userName);



    AppUser findByUserEmailAddress(String userEmailAddress);
Optional<AppUser> findEmailAdminByUserEmailAddress(String userEmailAddress);

}
