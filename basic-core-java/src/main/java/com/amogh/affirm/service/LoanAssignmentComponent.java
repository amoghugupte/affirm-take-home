package com.amogh.affirm.service;

import com.amogh.affirm.bean.Facility;
import com.amogh.affirm.bean.Loan;
import com.amogh.affirm.bean.LoanAssignment;
import com.amogh.affirm.calculator.YieldCalculator;
import com.amogh.affirm.covenent.CovenantFactory;
import com.amogh.affirm.covenent.CovenantStrategy;
import com.amogh.affirm.csv.repository.FacilityRepository;
import com.amogh.affirm.csv.repository.LoanAssignmentRepository;
import com.amogh.affirm.csv.repository.LoanRepository;
import com.amogh.affirm.csv.repository.YieldRepository;

import java.util.List;

public class LoanAssignmentComponent {
    private LoanAssignmentRepository loanAssignmentRepository = null;
    private YieldRepository yieldRepository = null;

    public void processLoans () {
        List<Facility> facilityList = FacilityRepository
                .builder()
                .folder("large")
                .build()
                .load()
                .getFacilities();

        LoanRepository loanRepository = LoanRepository
                .builder()
                .folder("large")
                .build()
                .initiate();

        loanAssignmentRepository = new LoanAssignmentRepository ();
        yieldRepository = new YieldRepository ();
        YieldCalculator yieldCalculator = new YieldCalculator ();
        Loan loan = null;
        while ((loan = loanRepository.nextLoan()) != null) {
            LoanAssignmentResult loanAssignmentResult = new LoanAssignmentResult();
            for (Facility facility: facilityList) {
                CovenantStrategy covenantStrategy = CovenantFactory.getCovenants(facility);
                if (covenantStrategy.canAssignFacility(loan)) {
                    loanAssignmentResult.addFacility(facility);
                }
            }

            if (loanAssignmentResult.getBestFacility() == null) {
                //Handle Default
                System.err.println(loan);
            } else {
                //todo update facility remainging amoutn
                loanAssignmentRepository.push(assignLoan (loan, loanAssignmentResult.getBestFacility()));
                yieldRepository.push (yieldCalculator.calculate(loan, loanAssignmentResult.getBestFacility()));
            }
        }

        loanAssignmentRepository.persist("C:\\Users\\amogh\\IdeaProjects\\OpenCsv\\src\\main\\resources\\large\\assignments.csv");
        yieldRepository.persist("C:\\Users\\amogh\\IdeaProjects\\OpenCsv\\src\\main\\resources\\large\\yields.csv");


    }

    private LoanAssignment assignLoan(Loan loan, Facility bestFacility) {
        bestFacility.setCurrAmount(bestFacility.getCurrAmount().subtract(loan.getAmount()));
        return new LoanAssignment(bestFacility, loan);
    }

    public static void main(String[] args) {
        new LoanAssignmentComponent ()
                .processLoans();
    }
}
