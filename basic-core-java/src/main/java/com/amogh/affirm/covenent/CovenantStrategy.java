package com.amogh.affirm.covenent;

import com.amogh.affirm.bean.Loan;

public interface CovenantStrategy {
    boolean canAssignFacility (Loan loan);
}
