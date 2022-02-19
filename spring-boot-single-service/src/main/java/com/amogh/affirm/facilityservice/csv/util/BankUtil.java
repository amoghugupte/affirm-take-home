package com.amogh.affirm.facilityservice.csv.util;

import com.amogh.affirm.facilityservice.csv.model.BankCsvEntity;
import com.amogh.affirm.facilityservice.csv.parser.BankParser;
import com.amogh.affirm.facilityservice.model.Bank;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BankUtil {

    public List<Bank> load (InputStream file) {
        List <BankCsvEntity> bankList = BankParser
                .builder()
                .inputStream(file)
                .build()
                .toBeans();
        CsvMapper entityToBeanMapper = Mappers.getMapper(CsvMapper.class);
        return bankList
                .stream()
                .flatMap(bankCsvEntity -> Stream.of(entityToBeanMapper.entityToBean(bankCsvEntity)))
                .collect(Collectors.toList());
    }
}
