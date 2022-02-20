package com.amogh.affirm.basic.csv.parser;

import com.amogh.affirm.basic.csv.entity.BankEntity;
import com.amogh.affirm.basic.csv.entity.CovenantEntity;
import com.amogh.affirm.basic.csv.entity.FacilityEntity;
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
public class CovenantParser {
    private String fileName = null;
    private ReaderWriter readerWriter = null;

    public List<CovenantEntity> toBeans(List <BankEntity> bankList, List <FacilityEntity> facilityList) throws URISyntaxException, IOException {
        Reader reader = null;
        try {
            reader = readerWriter.getReader(fileName);

            HeaderColumnNameMappingStrategy ms = new HeaderColumnNameMappingStrategy();
            ms.setType(CovenantEntity.class);

            CsvToBean cb = new CsvToBeanBuilder<>(reader)
                    .withType(CovenantEntity.class)
                    .withMappingStrategy(ms)
                    .build();

            List<CovenantEntity> readList = new ArrayList<>();
            Map<Integer, BankEntity> bankIdMap = getBankMap (bankList);
            Map<Integer, FacilityEntity> facilityIdMap = getFacilityMap (facilityList);
            Iterator<CovenantEntity> itr = cb.iterator();
            while (itr.hasNext()) {
                CovenantEntity covenant = itr.next();
                if (facilityIdMap.containsKey(covenant.getFacilityId())) {
                    facilityIdMap.get(covenant.getFacilityId()).addBannedState(covenant.getBannedState());
                    facilityIdMap.get(covenant.getFacilityId()).setMaxDefaultLikelihood(covenant.getMaxDefaultLikelihood());
                } else if (bankIdMap.containsKey(covenant.getBankId())) {
                    bankIdMap.get(covenant.getBankId()).addBannedState(covenant.getBannedState());
                    bankIdMap.get(covenant.getBankId()).setMaxDefaultLikelihood(covenant.getMaxDefaultLikelihood());
                }
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

    private Map<Integer, FacilityEntity> getFacilityMap (List <FacilityEntity> bankList) {
        Map<Integer, FacilityEntity> facilityIdMap = new HashMap<>();
        for (FacilityEntity facility:bankList) {
            facilityIdMap.put(facility.getId(), facility);
        }
        return facilityIdMap;
    }
}
