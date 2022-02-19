package com.amogh.affirm.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class Facility {
    private int id = -1;

    private int bankId = -1;

    private BigDecimal amount = null;

    private BigDecimal currAmount = null;

    private BigDecimal interestRate = null;

    private Bank bank = null;

    private Set<String> bannedStates = null;

    private BigDecimal maxDefaultLikelihood = null;

}
