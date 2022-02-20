package com.amogh.affirm.ms.facility.controller;

import com.amogh.affirm.ms.common.model.Loan;
import com.amogh.affirm.ms.common.model.LoanAssignment;
import com.amogh.affirm.ms.facility.business.LoanAssignmentService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/loan-assign/v1")
public class LoanAssignmentController {

    @Autowired
    private LoanAssignmentService loanAssignmentService = null;

    @PostMapping ("assign")
    public String assignLoan (@RequestBody  Loan loan) {
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
