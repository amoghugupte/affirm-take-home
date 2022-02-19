package com.amogh.affirm.loanservice.csv.util;

import com.amogh.affirm.facilityservice.csv.util.CsvMapper;
import com.amogh.affirm.loanservice.csv.model.LoanCsvEntity;
import com.amogh.affirm.loanservice.csv.parser.LoanParser;
import com.amogh.affirm.loanservice.model.Loan;
import org.mapstruct.factory.Mappers;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoanUtil {
    public List<Loan> load (InputStream inputStream) {
        try {
            List<LoanCsvEntity> loanCsvEntityList = LoanParser
                    .builder()
                    .inputStream(inputStream)
                    .build()
                    .load();
            CsvMapper entityToBeanMapper =  Mappers.getMapper(CsvMapper.class);

            return loanCsvEntityList
                    .stream()
                    .flatMap(loanCsvEntity -> Stream.of(entityToBeanMapper.entityToBean(loanCsvEntity)))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
