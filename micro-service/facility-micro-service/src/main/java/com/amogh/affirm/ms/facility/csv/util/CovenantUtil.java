package com.amogh.affirm.ms.facility.csv.util;

import com.amogh.affirm.ms.common.model.Covenant;
import com.amogh.affirm.ms.facility.csv.model.CovenantCsvEntity;
import com.amogh.affirm.ms.facility.csv.parser.CovenantParser;
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
