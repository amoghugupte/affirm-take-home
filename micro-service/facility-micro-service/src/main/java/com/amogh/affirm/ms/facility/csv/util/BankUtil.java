package com.amogh.affirm.ms.facility.csv.util;

import com.amogh.affirm.ms.common.model.Bank;
import com.amogh.affirm.ms.facility.csv.model.BankCsvEntity;
import com.amogh.affirm.ms.facility.csv.parser.BankParser;
import org.mapstruct.factory.Mappers;

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
