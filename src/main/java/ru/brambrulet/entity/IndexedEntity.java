package ru.brambrulet.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class IndexedEntity {

    @Id
    private Long internalId;

}
