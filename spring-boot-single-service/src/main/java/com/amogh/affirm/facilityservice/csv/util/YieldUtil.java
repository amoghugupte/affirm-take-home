package com.amogh.affirm.facilityservice.csv.util;

import com.amogh.affirm.facilityservice.csv.model.YieldCsvEntity;
import com.amogh.affirm.facilityservice.model.Yield;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.mapstruct.factory.Mappers;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class YieldUtil {
    public byte[] export (List<Yield> yieldList) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream() ;
        CsvMapper entityToBeanMapper = Mappers.getMapper(CsvMapper.class);

        List<YieldCsvEntity> yieldCsvEntityList =  yieldList
                .stream()
                .flatMap(loanAssignment -> Stream.of(entityToBeanMapper.beanToEntity(loanAssignment)))
                .collect(Collectors.toList());

        Writer writer = new BufferedWriter(new OutputStreamWriter (outputStream));
        StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build();

        sbc.write(yieldCsvEntityList);
        writer.flush();

        return outputStream.toByteArray();
    }
}
