package com.amogh.affirm.csv.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanEntity {
    @CsvBindByName(column = "interest_rate")
    private BigDecimal interestRate = null;

    @CsvBindByName(column = "amount")
    private BigDecimal amount = null;

    @CsvBindByName(column = "id")
    private int id = -1;

    @CsvBindByName(column = "default_likelihood")
    private BigDecimal defaultLikelihood = null;

    @CsvBindByName(column = "state")
    private String state = null;
}
