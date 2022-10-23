package com.bayzat.cryptotracker.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void notify(Long userId, String message){
        System.out.printf("%d alert was hit. Message: %s.%n", userId, message);
    }
}
