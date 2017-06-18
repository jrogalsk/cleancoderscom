package com.jrsoft.learning.cleancoderscom.business.acceptancetests.doubles;

import com.jrsoft.learning.cleancoderscom.business.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

public class GatewayUtilities<T extends Entity> {
    private HashMap<String, T> entities;

    public GatewayUtilities() {
        this.entities = new HashMap<String, T>();
    }

    public List<T> getEntities() {
        List<T> clonedEntities = new ArrayList<>();
        for (T entity : entities.values())
            addCloneToList(entity, clonedEntities);
        return clonedEntities;
    }

    private void addCloneToList(T entity, List<T> newEntities) {
        try {
            newEntities.add((T) entity.clone());
        } catch (CloneNotSupportedException e) {
            throw new UnClonnableEntity();
        }
    }

    public T save(T entity) {
        if (isNull(entity.getId()))
            entity.setId(UUID.randomUUID().toString());
        String id = entity.getId();
        saveCloneInMap(id, entity);
        return entity;
    }

    private void saveCloneInMap(String id, T enetity) {
        try {
            entities.put(id, enetity.clone());
        }
        catch (CloneNotSupportedException e) {
            throw new UnClonnableEntity();
        }
    }

    public void delete(T entity) {
        entities.remove(entity.getId());
    }

    private static class UnClonnableEntity extends RuntimeException {

    }
}
