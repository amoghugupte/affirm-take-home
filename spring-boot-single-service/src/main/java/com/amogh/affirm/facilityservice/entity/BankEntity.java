package com.amogh.affirm.facilityservice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
public class BankEntity extends BaseEntity {
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
}
