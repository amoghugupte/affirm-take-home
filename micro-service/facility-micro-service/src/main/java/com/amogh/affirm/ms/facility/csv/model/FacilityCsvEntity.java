package com.amogh.affirm.ms.facility.csv.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import java.math.BigDecimal;

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
