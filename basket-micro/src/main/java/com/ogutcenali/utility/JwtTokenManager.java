package com.ogutcenali.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;
import java.util.Optional;

@ControllerAdvice
public class JwtTokenManager {

    private final String password = "${PASSWORD}";
    private final Long extraTime = 1000L * 60 * 15;


    public Optional<String> createToken(Long authid) {
        String token = "";
        token = JWT.create().withAudience()
                .withClaim("id", authid)
                .withIssuer("Ali")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + extraTime))
                .sign(Algorithm.HMAC512(password));
        return Optional.of(token);
    }

    public Optional<Long> decodeToken(String token) {
        Algorithm algorithm = Algorithm.HMAC512(password);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("Ali").build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return Optional.of(decodedJWT.getClaim("id").asLong());
    }


}
