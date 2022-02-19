package com.amogh.affirm.facilityservice.business.covenant;

import com.amogh.affirm.loanservice.model.Loan;

import java.util.Set;

public class BannedStateCovenantStrategy implements CovenantStrategy {
    private Set<String> bannedStates = null;

    public BannedStateCovenantStrategy(Set<String> bannedStates) {
        this.bannedStates = bannedStates;
    }

    @Override
    public boolean canAssignFacility(Loan loan) {
        return bannedStates == null? true: !bannedStates.contains(loan.getState());
    }
}
