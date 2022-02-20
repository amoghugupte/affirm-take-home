package com.amogh.affirm.ms.facility.business.covenant;

import com.amogh.affirm.ms.common.model.Facility;
import com.amogh.affirm.ms.common.model.Loan;

import java.math.BigDecimal;

public class MaxAmountCovenantStrategy implements CovenantStrategy  {
    private Facility facility = null;

    public MaxAmountCovenantStrategy(Facility facility) {
        this.facility = facility;
    }

    @Override
    public boolean canAssignFacility(Loan loan) {
        return loan.getAmount().compareTo(facility.getCurrAmount()) <= 0;
    }

    public static void main(String[] args) {
        System.out.println(BigDecimal.TEN.compareTo(BigDecimal.ONE));
    }
}
