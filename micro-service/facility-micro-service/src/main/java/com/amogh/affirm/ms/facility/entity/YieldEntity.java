package com.amogh.affirm.ms.facility.entity;

import com.amogh.affirm.ms.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class YieldEntity extends BaseEntity {

    @Column(name = "facility_id")
    private int facilityId;

    @Column(name = "expected_yield")
    private long expectedYield;

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id = -1;

    public YieldEntity(int facilityId, long expectedYield) {
        this.facilityId = facilityId;
        this.expectedYield = expectedYield;
    }
}
