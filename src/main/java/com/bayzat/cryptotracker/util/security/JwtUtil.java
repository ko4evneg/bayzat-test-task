package com.bayzat.cryptotracker.util.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bayzat.cryptotracker.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    public static final String JWT_ISSUER = "Bayzat lnc.";
    public static final String USER_CLAIM = "user";
    private static final String JWT_SUBJECT = "User details";

    @Value("${bayzat.jwt.secret}")
    private String secret;
    private final ObjectMapper mapper;

    public String generateToken(User user) {
        return JWT.create()
                .withSubject(JWT_SUBJECT)
                .withClaim(USER_CLAIM, convertToJson(user))
                .withIssuer(JWT_ISSUER)
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(secret));
    }

    private String convertToJson(User user) {
        try {
            return mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new SecurityException("Failed token serialization!");
        }
    }

    public User getValidUserDetails(String jwtToken) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject(JWT_SUBJECT)
                .withIssuer(JWT_ISSUER)
                .build();

        DecodedJWT jwt = jwtVerifier.verify(jwtToken);

        return getUser(jwt);
    }

    private User getUser(DecodedJWT jwt) {
        try {
            return mapper.readValue(getClaimString(jwt, USER_CLAIM), User.class);
        } catch (JsonProcessingException e) {
            throw new SecurityException("Failed token deserialization!");
        }
    }

    private String getClaimString(DecodedJWT jwt, String claimName) {
        return jwt.getClaim(claimName).asString();
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
