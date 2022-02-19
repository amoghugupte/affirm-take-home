package com.amogh.affirm.csv.parser;

import com.amogh.affirm.csv.entity.BankEntity;
import com.amogh.affirm.csv.entity.FacilityEntity;
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
public class FacilityParser {
    private int skipLines = 0;
    private String fileName = null;

    public List<FacilityEntity> toBeans(List <BankEntity> bankList) throws URISyntaxException, IOException {
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(
                    ClassLoader.getSystemResource(fileName).toURI()));

            HeaderColumnNameMappingStrategy ms = new HeaderColumnNameMappingStrategy();
            ms.setType(FacilityEntity.class);

            CsvToBean cb = new CsvToBeanBuilder<>(reader)
                    .withType(BankEntity.class)
                    .withMappingStrategy(ms)
                    .build();

            List<FacilityEntity> readList = new ArrayList<>();
            Map<Integer, BankEntity> bankIdMap = getBankMap (bankList);
            Iterator<FacilityEntity> itr = cb.iterator();
            while (itr.hasNext()) {
                FacilityEntity csvBean = itr.next();
                csvBean.setBank(bankIdMap.get(csvBean.getBankId()));
                readList.add (csvBean);
            }
            return readList;
        } finally {
            try {
                reader.close();
            } catch (Exception exp) {
                //ignore
            }
        }
    }

    private Map<Integer, BankEntity> getBankMap(List <BankEntity> bankList) {
        Map<Integer, BankEntity> bankIdMap = new HashMap<>();
        for (BankEntity bank:bankList) {
            bankIdMap.put(bank.getId(), bank);
        }
        return bankIdMap;
    }
}
