package com.amogh.affirm.ms.facility.business.covenant;

import com.amogh.affirm.ms.common.model.Bank;
import com.amogh.affirm.ms.common.model.Facility;
import com.amogh.affirm.ms.common.model.Loan;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class CovenantStrategyTest {
    @Test
    public void checkMaxLikelyHood () {
        Set<String> bannedStates = new HashSet<>();
        bannedStates.add("AZ");
        Bank bank = new Bank();
        bank.setId(1);
        bank.setName("Affirm");
        bank.setMaxDefaultLikelihood(BigDecimal.valueOf(0.15));
        bank.setBannedStates(bannedStates);

        bannedStates = new HashSet<>();
        bannedStates.add("NJ");

        Facility facility = new Facility();
        facility.setBank(bank);
        facility.setCurrAmount(BigDecimal.valueOf(1000));
        facility.setAmount(BigDecimal.valueOf(1000));
        facility.setId(1);
        facility.setBankId(1);
        facility.setMaxDefaultLikelihood(BigDecimal.valueOf(0.05));
        facility.setBannedStates(bannedStates);

        Loan loan = new Loan();
        loan.setId(1);
        loan.setAmount(BigDecimal.valueOf(500));
        loan.setDefaultLikelihood(BigDecimal.valueOf(0.08));
        loan.setState("DE");

        CovenantStrategy covenantStrategy = CovenantFactory.getCovenants(facility);

        assert (!covenantStrategy.canAssignFacility(loan));

    }

    @Test
    public void checkCurrentAmount () {
        Set<String> bannedStates = new HashSet<>();
        bannedStates.add("AZ");
        Bank bank = new Bank();
        bank.setId(1);
        bank.setName("Affirm");
        bank.setMaxDefaultLikelihood(BigDecimal.valueOf(0.15));
        bank.setBannedStates(bannedStates);

        bannedStates = new HashSet<>();
        bannedStates.add("NJ");

        Facility facility = new Facility();
        facility.setBank(bank);
        facility.setCurrAmount(BigDecimal.valueOf(1000));
        facility.setAmount(BigDecimal.valueOf(1000));
        facility.setId(1);
        facility.setBankId(1);
        facility.setMaxDefaultLikelihood(BigDecimal.valueOf(0.05));
        facility.setBannedStates(bannedStates);

        Loan loan = new Loan();
        loan.setId(1);
        loan.setAmount(BigDecimal.valueOf(1500));
        loan.setDefaultLikelihood(BigDecimal.valueOf(0.03));
        loan.setState("DE");

        CovenantStrategy covenantStrategy = CovenantFactory.getCovenants(facility);

        assert (!covenantStrategy.canAssignFacility(loan));
    }


    @Test
    public void checkBannedState () {
        Set<String> bannedStates = new HashSet<>();
        bannedStates.add("AZ");
        Bank bank = new Bank();
        bank.setId(1);
        bank.setName("Affirm");
        bank.setMaxDefaultLikelihood(BigDecimal.valueOf(0.15));
        bank.setBannedStates(bannedStates);

        bannedStates = new HashSet<>();
        bannedStates.add("NJ");

        Facility facility = new Facility();
        facility.setBank(bank);
        facility.setCurrAmount(BigDecimal.valueOf(1000));
        facility.setAmount(BigDecimal.valueOf(1000));
        facility.setId(1);
        facility.setBankId(1);
        facility.setMaxDefaultLikelihood(BigDecimal.valueOf(0.05));
        facility.setBannedStates(bannedStates);

        Loan loan = new Loan();
        loan.setId(1);
        loan.setAmount(BigDecimal.valueOf(500));
        loan.setDefaultLikelihood(BigDecimal.valueOf(0.03));
        loan.setState("NJ");

        CovenantStrategy covenantStrategy = CovenantFactory.getCovenants(facility);

        assert (!covenantStrategy.canAssignFacility(loan));
    }


    @Test
    public void checkBankMaxLikelyHood () {
        Set<String> bannedStates = new HashSet<>();
        bannedStates.add("AZ");
        Bank bank = new Bank();
        bank.setId(1);
        bank.setName("Affirm");
        bank.setMaxDefaultLikelihood(BigDecimal.valueOf(0.15));
        bank.setBannedStates(bannedStates);

        bannedStates = new HashSet<>();
        bannedStates.add("NJ");

        Facility facility = new Facility();
        facility.setBank(bank);
        facility.setCurrAmount(BigDecimal.valueOf(1000));
        facility.setAmount(BigDecimal.valueOf(1000));
        facility.setId(1);
        facility.setBankId(1);
        facility.setBannedStates(bannedStates);

        Loan loan = new Loan();
        loan.setId(1);
        loan.setAmount(BigDecimal.valueOf(500));
        loan.setDefaultLikelihood(BigDecimal.valueOf(0.25));
        loan.setState("DE");

        CovenantStrategy covenantStrategy = CovenantFactory.getCovenants(facility);

        assert (!covenantStrategy.canAssignFacility(loan));

    }


    @Test
    public void checkBankBannedState () {
        Set<String> bannedStates = new HashSet<>();
        bannedStates.add("AZ");
        Bank bank = new Bank();
        bank.setId(1);
        bank.setName("Affirm");
        bank.setMaxDefaultLikelihood(BigDecimal.valueOf(0.15));
        bank.setBannedStates(bannedStates);

        Facility facility = new Facility();
        facility.setBank(bank);
        facility.setCurrAmount(BigDecimal.valueOf(1000));
        facility.setAmount(BigDecimal.valueOf(1000));
        facility.setId(1);
        facility.setBankId(1);
        facility.setMaxDefaultLikelihood(BigDecimal.valueOf(0.05));

        Loan loan = new Loan();
        loan.setId(1);
        loan.setAmount(BigDecimal.valueOf(500));
        loan.setDefaultLikelihood(BigDecimal.valueOf(0.03));
        loan.setState("AZ");

        CovenantStrategy covenantStrategy = CovenantFactory.getCovenants(facility);

        assert (!covenantStrategy.canAssignFacility(loan));
    }
}
