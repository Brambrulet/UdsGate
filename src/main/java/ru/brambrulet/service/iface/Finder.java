package ru.brambrulet.service.iface;

import java.util.function.BiPredicate;
import ru.brambrulet.entity.IndexedEntity;

public interface Finder<Entity extends IndexedEntity> {

    long genInternalId();

    default Entity findByInternalId(Long internalId) {
        return find((customer, id) -> id.equals(customer.getInternalId()), internalId);
    }

    <V> Entity find(BiPredicate<Entity, V> condition, V value);
}
