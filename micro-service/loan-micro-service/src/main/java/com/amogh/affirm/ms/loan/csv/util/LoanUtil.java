package com.amogh.affirm.ms.loan.csv.util;

import com.amogh.affirm.ms.common.model.Loan;
import com.amogh.affirm.ms.loan.csv.model.LoanCsvEntity;
import com.amogh.affirm.ms.loan.csv.parser.LoanParser;
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
