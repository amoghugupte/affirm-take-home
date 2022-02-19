package com.amogh.affirm.loanservice.controller;

import com.amogh.affirm.loanservice.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/loan/v1")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("load")
    public String uploadLoans (MultipartFile file) throws IOException {
        loanService.load(file.getInputStream());
        return "done!";
    }


    @PostMapping("assign")
    public String assign () {
        loanService.assign();
        return "success!";
    }
}
