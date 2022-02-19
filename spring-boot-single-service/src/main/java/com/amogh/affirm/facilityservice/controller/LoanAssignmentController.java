package com.amogh.affirm.facilityservice.controller;

import com.amogh.affirm.facilityservice.business.LoanAssignmentService;
import com.amogh.affirm.facilityservice.model.LoanAssignment;
import com.amogh.affirm.loanservice.model.Loan;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/loan-assign/v1")
public class LoanAssignmentController {

    @Autowired
    private LoanAssignmentService loanAssignmentService = null;

    @PostMapping ("assign")
    public String assignLoan (Loan loan) {
        loanAssignmentService.processLoan(loan);
        return "success";
    }

    @GetMapping (value = "assignments")
    public List<LoanAssignment> getAssignment () {
        return loanAssignmentService.getAssignment();
    }

    @GetMapping (value = "export", produces = "text/csv")
    public byte [] export () throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        return loanAssignmentService.export();
    }
}
