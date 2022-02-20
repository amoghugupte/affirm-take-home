package com.amogh.affirm.ms.facility.csv.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YieldCsvEntity extends BaseCsv {
    @CsvBindByName(column = "facility_id")
    private int facilityId;

    @CsvBindByName(column = "expected_yield")
    private long expectedYield;
}
