package com.bayzat.cryptotracker.service;

import com.bayzat.cryptotracker.exception.CryptoTrackerException;
import com.bayzat.cryptotracker.exception.ResourceNotFoundException;
import com.bayzat.cryptotracker.model.BaseEntity;
import com.bayzat.cryptotracker.model.to.BaseTo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public abstract class AbstractCrudService<E extends BaseEntity, T extends BaseTo> implements CrudService<E, T> {
    protected JpaRepository<E, Long> repository;
    @Autowired
    protected ModelMapper mapper;

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public E find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public E saveNew(E entity) {
        return repository.save(entity);
    }

    @Override
    public E save(E entity, Long id) {
        find(id);
        entity.setId(id);
        entity.setCreatedAt(new Date());
        return saveNew(entity);
    }

    @Override
    public E update(E updatedEntity, Long id) {
        E entity = find(id);
        mapper.map(updatedEntity, entity);
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        find(id);
        repository.deleteById(id);
    }

    @Override
    public Class<T> getToType() {
        throw new CryptoTrackerException("Operation not supported!");
    }

    @Override
    public Class<E> getEntityType() {
        throw new CryptoTrackerException("Operation not supported!");
    }
}
