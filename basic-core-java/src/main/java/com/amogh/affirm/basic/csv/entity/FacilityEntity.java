package com.amogh.affirm.basic.csv.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class FacilityEntity {
    @CsvBindByName(column = "id")
    private int id = -1;

    @CsvBindByName(column = "bank_id")
    private int bankId = -1;

    @CsvBindByName(column = "amount")
    private BigDecimal amount = null;

    @CsvBindByName(column = "interest_rate")
    private BigDecimal interestRate = null;

    private BankEntity bank = null;

    private Set<String> bannedStates = new HashSet<>();
    private BigDecimal maxDefaultLikelihood = null;

    public void addBannedState (String state) {
        bannedStates.add(state);
    }
}
