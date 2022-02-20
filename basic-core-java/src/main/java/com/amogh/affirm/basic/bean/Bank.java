package com.amogh.affirm.basic.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class Bank {
    private int id;

    private String name;

    private Set<String> bannedStates = new HashSet<>();
    private BigDecimal maxDefaultLikelihood = null;
}
