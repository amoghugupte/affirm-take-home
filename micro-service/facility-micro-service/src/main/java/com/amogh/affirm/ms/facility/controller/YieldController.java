package com.amogh.affirm.ms.facility.controller;

import com.amogh.affirm.ms.common.model.Yield;
import com.amogh.affirm.ms.facility.service.YieldService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/yield/v1")
public class YieldController {

    @Autowired
    private YieldService yieldService = null;

    @GetMapping (value = "assignments")
    public List<Yield> getYield () {
        return yieldService.getYield();
    }

    @GetMapping (value = "export", produces = "text/csv")
    public byte [] export () throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        return yieldService.export();
    }
}
