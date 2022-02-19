package com.amogh.affirm.facilityservice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class CovenantEntity extends BaseEntity {

    @Column
    private int id;

    @Column(name = "facility_id")
    private int facilityId = -1;

    @Column(name = "max_default_likelihood")
    private BigDecimal maxDefaultLikelihood = null;

    @Column(name = "bank_id")
    private int bankId = -1;

    @Column(name = "banned_state")
    private String bannedState = null;
}
