package com.amogh.affirm.facilityservice.csv.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class FacilityCsvEntity extends BaseCsv {
    @CsvBindByName(column = "id")
    private int id = -1;

    @CsvBindByName(column = "bank_id")
    private int bankId = -1;

    @CsvBindByName(column = "amount")
    private BigDecimal amount = null;

    @CsvBindByName(column = "interest_rate")
    private BigDecimal interestRate = null;
}
