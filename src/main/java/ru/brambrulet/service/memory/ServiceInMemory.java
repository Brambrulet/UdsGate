package ru.brambrulet.service.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import ru.brambrulet.entity.IndexedEntity;
import ru.brambrulet.service.iface.Finder;

public abstract class ServiceInMemory<Entity extends IndexedEntity> implements Finder<Entity> {
    protected List<Entity> entities = new ArrayList<>();
    private long generator = 0;

    public Entity persist(Entity entity) {
        if (entity.getInternalId() == null) {
            entity.setInternalId(genInternalId());
        }
        entities.add(entity);
        return entity;
    }

    public long genInternalId() {
        return ++generator;
    }

    @Override
    public <T> Entity find(BiPredicate<Entity, T> condition, T value) {
        for (Entity entity: entities) {
            if (condition.test(entity, value)) {
                return entity;
            }
        }
        return null;
    }
}
