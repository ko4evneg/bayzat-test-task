package com.bayzat.cryptotracker.service;

import com.bayzat.cryptotracker.model.BaseNamedEntity;
import com.bayzat.cryptotracker.model.Currency;

import java.util.List;

public interface CrudService<T extends BaseNamedEntity> {
    List<T> findAll();

    T find(Long id);

    T saveNew(T currency);

    T save(T currency, Long id);

    T update(T currency, Long id);

    void delete(Long id);
}
