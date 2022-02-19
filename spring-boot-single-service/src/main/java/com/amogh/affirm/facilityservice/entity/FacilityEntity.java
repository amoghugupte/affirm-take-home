package com.amogh.affirm.facilityservice.entity;

import lombok.Data;

import javax.persistence.*;
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
