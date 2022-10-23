package com.bayzat.cryptotracker.web;

import com.bayzat.cryptotracker.model.BaseNamedEntity;
import com.bayzat.cryptotracker.model.OwnedEntity;
import com.bayzat.cryptotracker.model.to.BaseNamedTo;
import com.bayzat.cryptotracker.service.AbstractOwnedEntityCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class AbstractOwnedEntityController<E extends BaseNamedEntity & OwnedEntity, T extends BaseNamedTo>
        extends AbstractEntityController<E, T> {
    protected AbstractOwnedEntityCrudService<E, T> ownedEntityCrudService;

    @Override
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(mapFromEntity(ownedEntityCrudService.findAllOwned(), ownedEntityCrudService.getToType()));
    }

    @Override
    public ResponseEntity<?> find(@PathVariable Long id) {
        return getOkResponse(ownedEntityCrudService.findOwned(id));
    }

    @Override
    public ResponseEntity<?> save(@RequestBody T to, @PathVariable Long id) {
        E savedEntity = ownedEntityCrudService.saveOwned(mapToEntity(to, service.getEntityType()), id);
        return getOkResponse(savedEntity);
    }

    @Override
    public ResponseEntity<?> update(@RequestBody T to, @PathVariable Long id) {
        E savedEntity = ownedEntityCrudService.updateOwned(mapToEntity(to, service.getEntityType()), id);
        return getOkResponse(savedEntity);
    }

    @Override
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ownedEntityCrudService.deleteOwned(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
