package com.amogh.affirm.ms.facility.csv.parser;

import com.amogh.affirm.ms.facility.csv.model.FacilityCsvEntity;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.Builder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Builder
public class FacilityParser {
    private int skipLines = 0;
    private InputStream inputStream = null;

    public List<FacilityCsvEntity> toBeans() {
        Reader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));

            HeaderColumnNameMappingStrategy ms = new HeaderColumnNameMappingStrategy();
            ms.setType(FacilityCsvEntity.class);

            CsvToBean cb = new CsvToBeanBuilder<>(reader)
                    .withType(FacilityCsvEntity.class)
                    .withMappingStrategy(ms)
                    .build();

            return cb.parse();
        } finally {
            try {
                reader.close();
            } catch (Exception exp) {
                //ignore
            }
        }
    }
}
