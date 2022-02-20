package com.amogh.affirm.ms.facility.business.covenant;

import com.amogh.affirm.ms.common.model.Loan;

public interface CovenantStrategy {
    boolean canAssignFacility (Loan loan);
}
