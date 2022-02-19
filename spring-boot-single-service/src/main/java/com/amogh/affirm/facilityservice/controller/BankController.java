package com.amogh.affirm.facilityservice.controller;

import com.amogh.affirm.facilityservice.model.Bank;
import com.amogh.affirm.facilityservice.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping ("/bank/v1")
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping("save")
    public String addBank (Bank bank) {
        bankService.save(bank);
        return "Saved";
    }

    @GetMapping("id")
    public Bank get (Integer id) {
        return bankService.getBank(id);
    }

    @GetMapping("current")
    public List<Bank> getBanks () {
        return bankService.getBanks();
    }

    @PostMapping (value = "load", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadBanks (MultipartFile file) throws IOException {
        bankService.load(file.getInputStream());
        return "done!";
    }
}