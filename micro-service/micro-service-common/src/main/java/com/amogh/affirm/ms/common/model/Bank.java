package com.amogh.affirm.ms.common.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class Bank extends Base {
    private int id;

    private String name;

    private Set<String> bannedStates = new HashSet<>();
    private BigDecimal maxDefaultLikelihood = null;

    public void addBannedState (String state) {
        bannedStates.add(state);
    }
}
