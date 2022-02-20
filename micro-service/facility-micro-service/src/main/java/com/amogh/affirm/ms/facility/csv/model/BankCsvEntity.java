package com.amogh.affirm.ms.facility.csv.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class BankCsvEntity extends BaseCsv{
    @CsvBindByName(column = "id")
    private int id;

    @CsvBindByName (column = "name")
    private String name;

    private Set<String> bannedStates = new HashSet<>();
    private BigDecimal maxDefaultLikelihood = null;

    public void addBannedState (String state) {
        bannedStates.add(state);
    }
}
