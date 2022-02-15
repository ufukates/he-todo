package com.hepsiemlak.todo.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtTimestampValidator;

@Configuration
@RequiredArgsConstructor
public class JwtValidatorConfiguration {

    @Bean
    public JwtTimestampValidator jwtTimestampValidator() {
        return new JwtTimestampValidator();
    }
}

