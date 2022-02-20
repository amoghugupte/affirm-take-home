package com.amogh.affirm.ms.facility.entity;

import com.amogh.affirm.ms.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@Entity
public class FacilityEntity extends BaseEntity {
    @Column(name = "id")
    private int id = -1;

    @Column(name = "amount")
    private BigDecimal amount = null;

    @Column(name = "curr_amount")
    private BigDecimal currAmount = null;

    @Column(name = "interest_rate")
    private BigDecimal interestRate = null;

    @Column(name = "bank_id")
    private int bankId;
}
