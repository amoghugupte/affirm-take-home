package com.amogh.affirm.facilityservice.business.covenant;

import com.amogh.affirm.loanservice.model.Loan;

public interface CovenantStrategy {
    boolean canAssignFacility (Loan loan);
}
