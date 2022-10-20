package com.bayzat.cryptotracker.service;

import com.bayzat.cryptotracker.model.BaseNamedEntity;

import java.util.List;

public interface CrudService<E extends BaseNamedEntity> {
    List<E> findAll();

    E find(Long id);

    E saveNew(E currency);

    E save(E currency, Long id);

    E update(E currency, Long id);

    void delete(Long id);
}
