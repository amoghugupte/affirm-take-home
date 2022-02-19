package com.amogh.affirm.loanservice.entity;

import com.amogh.affirm.facilityservice.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class LoanEntity extends BaseEntity {
    @Column(name = "interest_rate")
    private BigDecimal interestRate = null;

    @Column(name = "amount")
    private BigDecimal amount = null;

    @Column(name = "id")
    private int id = -1;

    @Column(name = "default_likelihood")
    private BigDecimal defaultLikelihood = null;

    @Column(name = "state")
    private String state = null;
}
