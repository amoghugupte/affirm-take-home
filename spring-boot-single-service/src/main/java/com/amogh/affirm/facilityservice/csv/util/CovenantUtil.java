package com.amogh.affirm.facilityservice.csv.util;

import com.amogh.affirm.facilityservice.csv.model.CovenantCsvEntity;
import com.amogh.affirm.facilityservice.csv.parser.CovenantParser;
import com.amogh.affirm.facilityservice.model.Covenant;
import org.mapstruct.factory.Mappers;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CovenantUtil {
    public List<Covenant> load (InputStream inputStream) {
        List <CovenantCsvEntity> bankList = CovenantParser
                .builder()
                .inputStream(inputStream)
                .build()
                .toBeans();
        CsvMapper entityToBeanMapper = Mappers.getMapper(CsvMapper.class);
        return bankList
                .stream()
                .flatMap(covenantCsvEntity -> Stream.of(entityToBeanMapper.entityToBean(covenantCsvEntity)))
                .collect(Collectors.toList());
    }
}
