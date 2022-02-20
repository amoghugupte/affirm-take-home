package com.amogh.affirm.basic.covenent;

import com.amogh.affirm.basic.bean.Loan;

import java.math.BigDecimal;

public class MaxDefaultLikelyHoodCovenantStrategy implements CovenantStrategy {
    private BigDecimal maxDefaultLikelihood = null;

    public MaxDefaultLikelyHoodCovenantStrategy(BigDecimal maxDefaultLikelihood) {
        this.maxDefaultLikelihood = maxDefaultLikelihood;
    }

    @Override
    public boolean canAssignFacility(Loan loan) {
        int compare = loan.getDefaultLikelihood().compareTo(maxDefaultLikelihood);
        return compare <= 0;
    }
}
