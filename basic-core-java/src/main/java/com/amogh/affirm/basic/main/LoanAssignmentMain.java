package com.amogh.affirm.basic.main;

import com.amogh.affirm.basic.bean.Facility;
import com.amogh.affirm.basic.bean.Loan;
import com.amogh.affirm.basic.bean.LoanAssignment;
import com.amogh.affirm.basic.calculator.LoanAssignmentResult;
import com.amogh.affirm.basic.calculator.YieldCalculator;
import com.amogh.affirm.basic.covenent.CovenantFactory;
import com.amogh.affirm.basic.covenent.CovenantStrategy;
import com.amogh.affirm.basic.csv.repository.FacilityRepository;
import com.amogh.affirm.basic.csv.repository.LoanAssignmentRepository;
import com.amogh.affirm.basic.csv.repository.LoanRepository;
import com.amogh.affirm.basic.csv.repository.YieldRepository;
import com.amogh.affirm.basic.readwrite.CustomReaderWriter;
import com.amogh.affirm.basic.readwrite.PreReaderWriter;
import com.amogh.affirm.basic.readwrite.ReaderWriter;

import java.util.List;

public class LoanAssignmentMain {
    private ReaderWriter readerWriter = null;

    public LoanAssignmentMain(ReaderWriter readerWriter) {
        this.readerWriter = readerWriter;
    }

    public void processLoans () {
        List<Facility> facilityList = FacilityRepository
                .builder()
                .readerWriter(readerWriter)
                .build()
                .load()
                .getFacilities();

        LoanRepository loanRepository = LoanRepository
                .builder()
                .readerWriter(readerWriter)
                .build()
                .initiate();


        LoanAssignmentRepository loanAssignmentRepository = new LoanAssignmentRepository (readerWriter);
        YieldRepository yieldRepository = new YieldRepository (readerWriter);
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
                //update facility remaining amount
                loanAssignmentRepository.push(assignLoan (loan, loanAssignmentResult.getBestFacility()));
                yieldRepository.push (yieldCalculator.calculate(loan, loanAssignmentResult.getBestFacility()));
            }
        }

        loanAssignmentRepository.persist();
        yieldRepository.persist();
    }

    private LoanAssignment assignLoan(Loan loan, Facility bestFacility) {
        bestFacility.setCurrAmount(bestFacility.getCurrAmount().subtract(loan.getAmount()));
        return new LoanAssignment(bestFacility, loan);
    }

    static final int PRE_MODE = 0;
    static final int CUSTOM_MODE = 1;

    public static void main(String[] args) {
        if (args.length == 0) {
            printHelpAndExit();
        }
        int mode = -1;
        String folderName = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-c")) {
                mode = CUSTOM_MODE;
            } else if (args[i].equals("-p")) {
                mode = PRE_MODE;
            } else {
                if (mode == -1 || folderName != null)
                    printHelpAndExit();

                folderName = args [i];
            }
        }

        if (folderName == null || mode == -1) {
            printHelpAndExit();
        }

        ReaderWriter readerWriter = getReaderWriter(mode, folderName);

        new LoanAssignmentMain(readerWriter)
                .processLoans();
    }

    static ReaderWriter getReaderWriter(int mode, String folderName) {
        ReaderWriter readerWriter = null;
        if (mode == PRE_MODE) {
            readerWriter = new PreReaderWriter(folderName);
        } else if (mode == CUSTOM_MODE) {
            readerWriter = new CustomReaderWriter(folderName);
        }
        return readerWriter;
    }


    static void printHelpAndExit () {
        System.out.println("usage: java com.amogh.affirm.basic.main.LoanAssignmentMain [-help] [-p large|small] [-c <folder-path>]");
        System.out.println("");
        System.out.println("These are the options:");
        System.out.println("");
        System.out.println("  -p             Pre-fixed mode, to use the test data provided with the assignment. The output assignments.csv and yields.csv, will be written in the current working dir.");
        System.out.println("  -c             Custom mode, to use new test data provide a folder path. it should contain the four csv - loans.csv, covenants.csv, facilities.csv and banks.csv. The output assignments.csv and yields.csv, will be written in the same dir");
        System.exit(-1);
    }
}
