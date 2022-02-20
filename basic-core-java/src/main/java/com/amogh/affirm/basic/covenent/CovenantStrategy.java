package com.amogh.affirm.basic.covenent;

import com.amogh.affirm.basic.bean.Loan;

public interface CovenantStrategy {
    boolean canAssignFacility (Loan loan);
}
