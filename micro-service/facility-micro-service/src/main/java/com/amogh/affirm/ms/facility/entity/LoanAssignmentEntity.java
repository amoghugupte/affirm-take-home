package com.amogh.affirm.ms.facility.entity;

import com.amogh.affirm.ms.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoanAssignmentEntity extends BaseEntity {

    @Column(name = "loan_id")
    private int loanId = -1;

    @Column(name = "facility_id")
    private int facilityId = -1;
}
