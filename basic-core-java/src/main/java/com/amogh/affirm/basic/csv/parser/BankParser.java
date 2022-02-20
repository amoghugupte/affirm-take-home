package com.amogh.affirm.basic.csv.parser;

import com.amogh.affirm.basic.csv.entity.BankEntity;
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
import java.util.List;

@Builder
public class BankParser {
    private int skipLines = 0;
    private String fileName = null;
    private ReaderWriter readerWriter = null;

    public List<BankEntity> toBeans( ) throws URISyntaxException, IOException {
        Reader reader = null;
        try {
            reader = readerWriter.getReader(fileName);

            HeaderColumnNameMappingStrategy ms = new HeaderColumnNameMappingStrategy();
            ms.setType(BankEntity.class);

            CsvToBean cb = new CsvToBeanBuilder<>(reader)
                    .withType(BankEntity.class)
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
