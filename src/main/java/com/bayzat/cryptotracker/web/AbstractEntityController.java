package com.bayzat.cryptotracker.web;

import com.bayzat.cryptotracker.model.BaseNamedEntity;
import com.bayzat.cryptotracker.model.to.BaseNamedTo;
import com.bayzat.cryptotracker.service.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;

public abstract class AbstractEntityController<E extends BaseNamedEntity, T extends BaseNamedTo> {
    protected CrudService<E, T> service;
    @Autowired
    protected ModelMapper mapper;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(mapFromEntity(service.findAll(), service.getToType()));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        return getOkResponse(service.find(id));
    }

    @PostMapping
    public ResponseEntity<?> saveNew(@RequestBody T to) {
        E savedEntity = service.saveNew(mapToEntity(to, service.getEntityType()));
        return getOkResponse(savedEntity);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> save(@RequestBody T to, @PathVariable Long id) {
        E savedEntity = service.save(mapToEntity(to, service.getEntityType()), id);
        return getOkResponse(savedEntity);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> update(@RequestBody T to, @PathVariable Long id) {
        E savedEntity = service.update(mapToEntity(to, service.getEntityType()), id);
        return getOkResponse(savedEntity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    
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

    protected ResponseEntity<T> getOkResponse(E savedEntity) {
        return ResponseEntity.ok(mapFromEntity(savedEntity, service.getToType()));
    }
}
