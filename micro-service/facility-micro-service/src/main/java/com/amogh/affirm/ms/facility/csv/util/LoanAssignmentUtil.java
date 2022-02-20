package com.amogh.affirm.ms.facility.csv.util;

import com.amogh.affirm.ms.common.model.LoanAssignment;
import com.amogh.affirm.ms.facility.csv.model.LoanAssignmentCsvEntity;
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

public class LoanAssignmentUtil {
    public byte[] export (List<LoanAssignment> loanAssignmentEntities) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream() ;
        CsvMapper entityToBeanMapper = Mappers.getMapper(CsvMapper.class);

        List<LoanAssignmentCsvEntity> loanAssignmentCsvEntityList = loanAssignmentEntities
                .stream()
                .flatMap(loanAssignment -> Stream.of(entityToBeanMapper.beanToEntity(loanAssignment)))
                .collect(Collectors.toList());

        Writer writer = new BufferedWriter(new OutputStreamWriter (outputStream));
        StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build();

        sbc.write(loanAssignmentCsvEntityList);
        writer.flush();

        return outputStream.toByteArray();
    }
}
