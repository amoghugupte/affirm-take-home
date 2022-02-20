package com.amogh.affirm.ms.loan.client;

import com.amogh.affirm.ms.common.model.Loan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "facility-service", configuration = FeignConfig.class)
public interface LoanAssignmentService {
    @PostMapping("/loan-assign/v1/assign")
    public String processLoan (Loan loan);
}
