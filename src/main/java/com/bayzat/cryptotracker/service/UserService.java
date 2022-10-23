package com.bayzat.cryptotracker.service;

import com.bayzat.cryptotracker.model.User;
import com.bayzat.cryptotracker.model.to.UserCredentialsTo;
import com.bayzat.cryptotracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class UserService extends AbstractCrudService<User, UserCredentialsTo> {
    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        repository = userRepository;
    }

    public User find(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new SecurityException("User " + username + " not found!"));
    }

    @Override
    public Class<User> getEntityType() {
        return User.class;
    }
}
