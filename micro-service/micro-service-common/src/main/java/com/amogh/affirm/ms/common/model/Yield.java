package com.amogh.affirm.ms.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Yield extends Base {
    private int facilityId;
    private long expectedYield;
}
