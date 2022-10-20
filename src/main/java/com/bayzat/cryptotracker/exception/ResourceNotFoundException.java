package com.bayzat.cryptotracker.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends CryptoTrackerException {
    private Long resourceId;

    public ResourceNotFoundException(Long resourceId) {
        super();
        this.resourceId = resourceId;
    }
}
