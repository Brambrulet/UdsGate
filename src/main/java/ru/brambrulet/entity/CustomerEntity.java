package ru.brambrulet.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import ru.brambrulet.json.enums.Gender;

@Data
@Entity
public class CustomerEntity extends IndexedEntity{

    private Long id;

    @Column(name = "uds_id")
    private Long udsId;

    private UUID uid;

    private String avatar;

    private String displayName;

    private Gender gender;

    private String phone;

    @Column(name = "membership_tier_uid")
    private String membershipTierUid;
}
