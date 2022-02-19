package com.amogh.affirm.facilityservice.business.calculator;

import com.amogh.affirm.facilityservice.model.Facility;
import com.amogh.affirm.facilityservice.model.Yield;
import com.amogh.affirm.loanservice.model.Loan;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class YieldCalculator {
    public Yield calculate (Loan loan, Facility facility) {
        //(1 - default_likelihood) * loan_interest_rate * amount
        BigDecimal loanInterest = BigDecimal.ONE
                .subtract(loan.getDefaultLikelihood())
                    .multiply(loan.getInterestRate())
                    .multiply(loan.getAmount());

        //default_likelihood * amount
        BigDecimal defaultLikelyhood = loan.getDefaultLikelihood()
                .multiply(loan.getAmount());

        //facility_interest_rate * amount
        BigDecimal facilityInterest = facility.getInterestRate()
                .multiply(loan.getAmount());

        BigDecimal yieldBigD = loanInterest
                .subtract(defaultLikelyhood)
                .subtract(facilityInterest);

        int yieldInt = yieldBigD
                .setScale(0, RoundingMode.HALF_UP)
                .intValue();
        return new Yield (facility.getId(), yieldInt);
    }
}
