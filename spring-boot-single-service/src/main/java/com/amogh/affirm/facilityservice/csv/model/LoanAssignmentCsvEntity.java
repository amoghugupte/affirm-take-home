package com.amogh.affirm.facilityservice.csv.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanAssignmentCsvEntity extends BaseCsv {
    @CsvBindByName(column = "loan_id")
    private int loanId = -1;

    @CsvBindByName(column = "facility_id")
    private int facilityId = -1;
}
