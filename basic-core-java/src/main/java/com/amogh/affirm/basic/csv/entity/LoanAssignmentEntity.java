package com.amogh.affirm.basic.csv.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanAssignmentEntity {
    @CsvBindByName(column = "loan_id")
    private int loanId = -1;

    @CsvBindByName(column = "facility_id")
    private int facilityId = -1;
}
