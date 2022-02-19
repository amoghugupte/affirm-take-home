package com.amogh.affirm.service;

import com.amogh.affirm.bean.Facility;
import lombok.Data;

@Data
public class LoanAssignmentResult {
    private Facility bestFacility = null;

    public void addFacility (Facility facility) {
        if (bestFacility == null
                || bestFacility.getInterestRate().compareTo(facility.getInterestRate()) == 1
                || (bestFacility.getInterestRate().compareTo(facility.getInterestRate()) == 0
                        && bestFacility.getCurrAmount().compareTo(facility.getCurrAmount()) == -1)) {
            bestFacility = facility;
        }
    }
}
