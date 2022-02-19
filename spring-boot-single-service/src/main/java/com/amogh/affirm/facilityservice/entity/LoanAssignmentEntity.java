package com.amogh.affirm.facilityservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
