package com.noticiero.udc.infrastructure.config;

import com.noticiero.udc.application.ports.out.EmailServicePort;
import com.noticiero.udc.application.services.UserService;
import com.noticiero.udc.infrastructure.adapters.persistence.UserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public UserService userService (UserRepositoryAdapter userRepositoryAdapter, EmailServicePort emailServicePort ) {
        return new UserService(userRepositoryAdapter, emailServicePort);
    }

}
