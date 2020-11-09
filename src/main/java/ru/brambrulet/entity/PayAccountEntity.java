package ru.brambrulet.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity(name = "pay_code")
@Data
public class PayAccountEntity  extends IndexedEntity{

    private Integer id;

    @Column(name = "short_play_code")
    private Long shortPlayCode;

    @Column(name = "long_play_code")
    private String longPlayCode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @Column(name = "order_summ")
    private BigDecimal orderSumm;

    private Boolean used;
}
