package com.bayzat.cryptotracker.web;

import com.bayzat.cryptotracker.model.User;
import com.bayzat.cryptotracker.model.to.UserCredentialsTo;
import com.bayzat.cryptotracker.service.UserService;
import com.bayzat.cryptotracker.util.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping(value = "authenticate")
    public ResponseEntity<?> authenticate(@RequestBody UserCredentialsTo userCredentialsTo) {
        User user = userService.find(userCredentialsTo.getUsername());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, userCredentialsTo.getPassword());
        tryAuthenticate(token);
        return ResponseEntity.ok(jwtUtil.generateToken(user));
    }

    private void tryAuthenticate(UsernamePasswordAuthenticationToken token) {
        try {
            authenticationManager.authenticate(token);
        } catch (AuthenticationException e) {
            throw new SecurityException(e.getMessage());
        }
    }
}
