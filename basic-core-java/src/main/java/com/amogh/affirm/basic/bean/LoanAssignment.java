package com.amogh.affirm.basic.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanAssignment {
    private Facility facility = null;
    private Loan loan = null;
}
