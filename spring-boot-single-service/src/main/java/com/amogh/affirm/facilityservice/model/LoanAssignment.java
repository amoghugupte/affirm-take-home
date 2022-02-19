package com.amogh.affirm.facilityservice.model;

import com.amogh.affirm.loanservice.model.Loan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanAssignment extends Base {
    private int loanId = -1;

    private int facilityId = -1;
}
