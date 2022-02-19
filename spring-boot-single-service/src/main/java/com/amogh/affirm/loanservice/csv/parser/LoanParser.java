package com.amogh.affirm.loanservice.csv.parser;

import com.amogh.affirm.loanservice.csv.model.LoanCsvEntity;
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
public class LoanParser {
    private InputStream inputStream = null;

    public List<LoanCsvEntity> load () {
        Reader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            HeaderColumnNameMappingStrategy ms = new HeaderColumnNameMappingStrategy();
            ms.setType(LoanCsvEntity.class);

            CsvToBean cb = new CsvToBeanBuilder<>(reader)
                    .withType(LoanCsvEntity.class)
                    .withMappingStrategy(ms)
                    .build();
            return cb.parse();
        } finally {
            try {
                reader.close();
            } catch (Exception exception) {
                //ignore
            }
        }
    }
}
