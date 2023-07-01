package com.Email.registration.Emailregistration.appCoonfig;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
public class facebookConfig {
    @Value("${facebook.key}")
    private String key;


    @Value("${facebook.secret}")
    private String secret;



}
