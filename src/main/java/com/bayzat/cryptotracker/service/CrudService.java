package com.bayzat.cryptotracker.service;

import com.bayzat.cryptotracker.model.BaseEntity;
import com.bayzat.cryptotracker.model.to.BaseTo;

import java.util.List;

public interface CrudService<E extends BaseEntity, T extends BaseTo> {
    List<E> findAll();

    E find(Long id);

    E saveNew(E currency);

    E save(E currency, Long id);

    E update(E currency, Long id);

    void delete(Long id);

    Class<T> getToType();

    Class<E> getEntityType();
}
