package com.amogh.affirm.ms.facility.service;

import com.amogh.affirm.ms.common.model.Bank;
import com.amogh.affirm.ms.common.model.Covenant;
import com.amogh.affirm.ms.common.model.Facility;
import com.amogh.affirm.ms.facility.csv.util.CovenantUtil;
import com.amogh.affirm.ms.facility.entity.CovenantEntity;
import com.amogh.affirm.ms.facility.repository.CovenantRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CovenantService {
    @Autowired
    private CovenantRepository covenantRepository;

    @Transactional
    public void load (InputStream inputStream) throws IOException {
        EntityToBeanMapper entityToBeanMapper = Mappers.getMapper(EntityToBeanMapper.class);
        List <Covenant> covenantList = new CovenantUtil()
                .load(inputStream);

        List <CovenantEntity> covenantEntityList = covenantList.stream()
                .flatMap(covenant -> Stream.of(entityToBeanMapper.beanToEntity(covenant)))
                .collect(Collectors.toList());

        saveAll(covenantEntityList);
    }

    private void saveAll(List <CovenantEntity> facilityEntityList) {
        List <Integer> facilityIds = facilityEntityList.stream()
                .flatMap(facilityEntity -> Stream.of(facilityEntity.getId()))
                .collect(Collectors.toList());

        covenantRepository.updateCovenant(facilityIds);
        covenantRepository.saveAll(facilityEntityList);
    }

    private Map<Integer, Bank> getBankMap(List <Bank> bankList) {
        Map<Integer, Bank> bankIdMap = new HashMap<>();
        for (Bank bank:bankList) {
            bankIdMap.put(bank.getId(), bank);
        }
        return bankIdMap;
    }

    private Map<Integer, Facility> getFacilityMap (List <Facility> facilityList) {
        Map<Integer, Facility> facilityIdMap = new HashMap<>();
        for (Facility facility:facilityList) {
            facilityIdMap.put(facility.getId(), facility);
        }
        return facilityIdMap;
    }
}
