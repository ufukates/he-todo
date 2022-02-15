package com.hepsiemlak.todo.service;

import com.hepsiemlak.todo.domain.dto.response.TokenResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TokenServiceTest {

    private TokenService tokenService;

    @BeforeEach
    public void setUp() throws NoSuchAlgorithmException {
        KeyPairGenerator fact = KeyPairGenerator.getInstance("RSA");
        fact.initialize(2048, new SecureRandom());
        KeyPair keyPair = fact.generateKeyPair();
        tokenService = new TokenService(keyPair);
    }

    @Test
    public void should_get_token() {
        // Given
        final String userId = "userId";

        // When
        TokenResponse actualTokenResponse = tokenService.getToken(userId);

        // Then
        assertEquals("bearer", actualTokenResponse.getTokenType());
        assertNotNull(actualTokenResponse.getAccessToken());
    }
}
