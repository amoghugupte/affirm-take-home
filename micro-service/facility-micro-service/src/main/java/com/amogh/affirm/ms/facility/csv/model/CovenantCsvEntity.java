package com.amogh.affirm.ms.facility.csv.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CovenantCsvEntity extends BaseCsv {
    @CsvBindByName(column = "facility_id")
    private int facilityId = -1;

    @CsvBindByName(column = "max_default_likelihood")
    private BigDecimal maxDefaultLikelihood = null;

    @CsvBindByName(column = "bank_id")
    private int bankId = -1;

    @CsvBindByName(column = "banned_state")
    private String bannedState = null;
}
