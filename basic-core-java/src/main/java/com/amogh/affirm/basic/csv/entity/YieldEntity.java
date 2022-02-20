package com.amogh.affirm.basic.csv.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YieldEntity {
    @CsvBindByName(column = "facility_id")
    private int facilityId;

    @CsvBindByName(column = "expected_yield")
    private long expectedYield;
}
