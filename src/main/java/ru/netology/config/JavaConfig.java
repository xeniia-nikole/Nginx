package ru.netology.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.repository.UserRepository;
import ru.netology.service.AuthorizationService;

@Configuration
public class JavaConfig {

    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }

    @Bean
    public AuthorizationService authorizationService() {
        return new AuthorizationService(userRepository());
    }

}
