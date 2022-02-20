package com.amogh.affirm.basic.csv.parser;

import com.amogh.affirm.basic.csv.entity.LoanEntity;
import com.amogh.affirm.basic.readwrite.ReaderWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.Builder;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Builder
public class LoanParser {
    private int skipLines = 0;
    private String fileName = null;
    private ReaderWriter readerWriter = null;

    private Reader reader = null;
    private Iterator<LoanEntity> itr = null;

    public LoanParser load () throws URISyntaxException, IOException {
        reader = readerWriter.getReader(fileName);

        HeaderColumnNameMappingStrategy ms = new HeaderColumnNameMappingStrategy();
        ms.setType(LoanEntity.class);

        CsvToBean cb = new CsvToBeanBuilder<>(reader)
                .withType(LoanEntity.class)
                .withMappingStrategy(ms)
                .build();
        itr = cb.iterator();
        return this;
    }

    public LoanEntity getNextLoan () {
        if (itr.hasNext()) {
            return itr.next();
        } else {
            try {
                reader.close();
            } catch (Exception exception) {
                //ignore;
            }
            return null;
        }
    }
}
