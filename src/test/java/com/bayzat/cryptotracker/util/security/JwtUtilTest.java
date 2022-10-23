package com.bayzat.cryptotracker.util.security;

import com.bayzat.cryptotracker.model.Role;
import com.bayzat.cryptotracker.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class JwtUtilTest {
    private JwtUtil jwtUtil = new JwtUtil(new ObjectMapper());

    @BeforeEach
    public void init() {
        jwtUtil.setSecret("s3cret");
    }

    @Test
    public void encryptedTokenReadSuccess() {
        User expectedUser = new User();
        expectedUser.setUsername("testUser");
        expectedUser.setPassword("testPass");
        expectedUser.setRoles(Set.of(new Role("r1"), new Role("r2")));

        String token = jwtUtil.generateToken(expectedUser);
        User actualUser = jwtUtil.getValidUserDetails(token);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void tokenDoesNotContainPassword() {
        User expectedUser = new User();
        expectedUser.setPassword("testPass");

        String token = jwtUtil.generateToken(expectedUser);
        User decryptedToken = jwtUtil.getValidUserDetails(token);

        assertNull(decryptedToken.getPassword());
    }
}