package com.amogh.affirm.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Loan {
    private BigDecimal interestRate = null;

    private BigDecimal amount = null;

    private int id = -1;

    private BigDecimal defaultLikelihood = null;

    private String state = null;
}
