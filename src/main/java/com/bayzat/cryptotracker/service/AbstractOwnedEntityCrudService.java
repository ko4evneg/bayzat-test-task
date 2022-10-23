package com.bayzat.cryptotracker.service;

import com.bayzat.cryptotracker.exception.ResourceNotFoundException;
import com.bayzat.cryptotracker.model.BaseEntity;
import com.bayzat.cryptotracker.model.OwnedEntity;
import com.bayzat.cryptotracker.model.User;
import com.bayzat.cryptotracker.model.to.BaseTo;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.List;

public abstract class AbstractOwnedEntityCrudService<E extends BaseEntity & OwnedEntity, T extends BaseTo> extends AbstractCrudService<E, T> {
    public E findOwned(Long id) {
        E entity = super.find(id);
        if (belongsActiveUser(entity)) {
            return entity;
        }
        throw new ResourceNotFoundException(id);
    }

    public E updateOwned(E updatedEntity, Long id) {
        E entity = super.find(id);
        if (belongsActiveUser(entity)) {
            mapper.map(updatedEntity, entity);
            return repository.save(entity);
        }
        throw new ResourceNotFoundException(id);
    }

    public void deleteOwned(Long id) {
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

    public abstract List<E> findAllOwned();

    @Override
    public E saveNew(E entity) {
        entity.setUser(getActiveUser());
        return super.saveNew(entity);
    }

    public E saveOwned(E entity, Long id) {
        findOwned(id);
        entity.setId(id);
        entity.setCreatedAt(new Date());
        return saveNew(entity);
    }
}
