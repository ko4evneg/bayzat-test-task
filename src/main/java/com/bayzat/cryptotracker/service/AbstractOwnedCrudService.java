package com.bayzat.cryptotracker.service;

import com.bayzat.cryptotracker.exception.ResourceNotFoundException;
import com.bayzat.cryptotracker.model.BaseEntity;
import com.bayzat.cryptotracker.model.OwnedEntity;
import com.bayzat.cryptotracker.model.User;
import com.bayzat.cryptotracker.model.to.BaseTo;
import org.springframework.security.core.context.SecurityContextHolder;

public class AbstractOwnedCrudService<E extends BaseEntity & OwnedEntity, T extends BaseTo> extends AbstractCrudService<E, T> {
    public E findOwned(Long id) {
        E entity = super.find(id);
        if (belongsActiveUser(entity)) {
            return entity;
        }
        throw new ResourceNotFoundException(id);
    }

    protected E updateOwned(E updatedEntity, Long id) {
        E entity = super.find(id);
        if (belongsActiveUser(entity)) {
            mapper.map(updatedEntity, entity);
            return repository.save(entity);
        }
        throw new ResourceNotFoundException(id);
    }

    protected void deleteOwned(Long id) {
        E entity = super.find(id);
        if (belongsActiveUser(entity)) {
            repository.deleteById(id);
            return;
        }
        throw new ResourceNotFoundException(id);
    }

    protected boolean belongsActiveUser(E entity) {
        return entity.getUser().equals(getActiveUser());
    }

    protected User getActiveUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
