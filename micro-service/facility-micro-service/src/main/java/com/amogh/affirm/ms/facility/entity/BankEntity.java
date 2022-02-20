package com.amogh.affirm.ms.facility.entity;

import com.amogh.affirm.ms.common.entity.BaseEntity;
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
