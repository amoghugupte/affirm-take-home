package com.amogh.affirm.facilityservice.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class Facility extends Base {
    private int id = -1;

    private int bankId = -1;

    private BigDecimal amount = null;

    private BigDecimal currAmount = null;

    private BigDecimal interestRate = null;

    private Bank bank = null;

    private Set<String> bannedStates = new HashSet<>();

    private BigDecimal maxDefaultLikelihood = null;

    public void addBannedState (String state) {
        bannedStates.add(state);
    }
}
