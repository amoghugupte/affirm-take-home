package com.amogh.affirm.facilityservice.business.covenant;

import com.amogh.affirm.loanservice.model.Loan;

import java.util.ArrayList;
import java.util.List;

public class CovenantCompositeStrategy implements CovenantStrategy {

    private List<CovenantStrategy> covenantList = new ArrayList<>();

    public CovenantCompositeStrategy(List<CovenantStrategy> covenantList) {
        this.covenantList = covenantList;
    }

    @Override
    public boolean canAssignFacility(Loan loan) {
        for (CovenantStrategy covenantStrategy: covenantList) {
            boolean canAssign = covenantStrategy.canAssignFacility(loan);
            if (!canAssign) return false;
        }
        return true;
    }
}
