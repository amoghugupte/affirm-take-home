package com.amogh.affirm.facilityservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
