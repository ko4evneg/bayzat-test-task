package com.bayzat.cryptotracker.web;

import com.bayzat.cryptotracker.model.BaseNamedEntity;
import com.bayzat.cryptotracker.model.to.BaseNamedTo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

public class EntityController<E extends BaseNamedEntity, T extends BaseNamedTo> {
    @Autowired
    protected ModelMapper mapper;

    protected T mapFromEntity(E entity, Class<T> aClass) {
        return mapper.map(entity, aClass);
    }

    protected E mapToEntity(T to, Class<E> aClass) {
        return mapper.map(to, aClass);
    }

    protected List<T> mapFromEntity(Collection<E> toCollection, Class<T> aClass) {
        return toCollection.stream()
                .map(entity -> mapFromEntity(entity, aClass))
                .toList();
    }
}
