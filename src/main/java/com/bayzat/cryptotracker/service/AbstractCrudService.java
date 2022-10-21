package com.bayzat.cryptotracker.service;

import com.bayzat.cryptotracker.exception.ResourceNotFoundException;
import com.bayzat.cryptotracker.model.BaseNamedEntity;
import com.bayzat.cryptotracker.model.to.BaseNamedTo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public abstract class AbstractCrudService<E extends BaseNamedEntity, T extends BaseNamedTo> implements CrudService<E, T> {
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
    public E save(E currency, Long id) {
        findIfExists(id);
        currency.setId(id);
        currency.setCreatedAt(new Date());
        return saveNew(currency);
    }

    @Override
    public E update(E updatedCurrency, Long id) {
        E currency = findIfExists(id);
        mapper.map(updatedCurrency, currency);
        return repository.save(currency);
    }

    @Override
    public void delete(Long id) {
        findIfExists(id);
        repository.deleteById(id);
    }

    private E findIfExists(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
