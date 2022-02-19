package com.amogh.affirm.covenent;

import com.amogh.affirm.bean.Loan;

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
