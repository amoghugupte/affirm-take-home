package com.amogh.affirm.facilityservice.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Covenant extends Base  {
    private int id;

    private int facilityId = -1;

    private BigDecimal maxDefaultLikelihood = null;

    private int bankId = -1;

    private String bannedState = null;
}
