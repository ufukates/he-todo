package com.hepsiemlak.todo.service;

import com.hepsiemlak.todo.domain.dto.response.TokenResponse;
import com.hepsiemlak.todo.domain.enums.UserType;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import java.security.KeyPair;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
public class TokenService {
    private final KeyPair keyPair;
    private static final Long tokenExpirationTime = 5184000L;

    @SneakyThrows
    public TokenResponse getToken(String email) {
        JWSSigner signer = new RSASSASigner(keyPair.getPrivate());
        String tokenId = UUID.randomUUID().toString();

        SignedJWT signedJwt = getSignedJwt(email, signer, tokenId);
        return TokenResponse
                .builder()
                .accessToken(signedJwt.serialize())
                .tokenType("bearer")
                .expiresIn(tokenExpirationTime)
                .build();
    }

    private SignedJWT getSignedJwt(String email, JWSSigner signer, String tokenId) throws JOSEException {
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .claim("scope", UserType.USER.getAuthority())
                .expirationTime(Date.from(Instant.now().plus(tokenExpirationTime, ChronoUnit.SECONDS)))
                .jwtID(tokenId)
                .issueTime(new Date())
                .build();
        SignedJWT signedJwt = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(keyPair.getPrivate().toString()).build(),
                jwtClaimsSet);
        signedJwt.sign(signer);
        return signedJwt;
    }
}
