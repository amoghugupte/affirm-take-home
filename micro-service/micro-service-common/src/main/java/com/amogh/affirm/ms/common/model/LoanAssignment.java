package com.amogh.affirm.ms.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanAssignment extends Base {
    private int loanId = -1;

    private int facilityId = -1;
}
