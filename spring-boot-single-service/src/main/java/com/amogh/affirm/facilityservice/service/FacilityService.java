package com.amogh.affirm.facilityservice.service;

import com.amogh.affirm.facilityservice.csv.util.FacilityUtil;
import com.amogh.affirm.facilityservice.entity.CovenantEntity;
import com.amogh.affirm.facilityservice.entity.FacilityEntity;
import com.amogh.affirm.facilityservice.model.Bank;
import com.amogh.affirm.facilityservice.model.Facility;
import com.amogh.affirm.facilityservice.repository.CovenantRepository;
import com.amogh.affirm.facilityservice.repository.FacilityRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FacilityService {
    @Autowired
    private BankService bankService;

    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private CovenantRepository covenantRepository;

    @Transactional
    public void save (Facility facility) {
        EntityToBeanMapper entityToBeanMapper = Mappers.getMapper(EntityToBeanMapper.class);
        facilityRepository.updateFacility(Arrays.asList(facility.getId()));
        facility.setKeyId(null);
        facilityRepository.save(entityToBeanMapper.beanToEntity(facility));
    }

    public List<Facility> getCurrentFacilities () {
        EntityToBeanMapper entityToBeanMapper = Mappers.getMapper(EntityToBeanMapper.class);
        List<FacilityEntity> facilityEntityList = facilityRepository.findAll();
        List<Facility> facilityList = facilityEntityList.stream()
                .flatMap(facilityEntity -> Stream.of(entityToBeanMapper.facilityEntityToBean(facilityEntity)))
                .collect(Collectors.toList());
        return getFacilities(facilityList);
    }

    public List<Facility> getFacilitiesByAmount (BigDecimal amount) {
        List<FacilityEntity> facilityEntityList = facilityRepository.findByCurrentAmount(amount);
        EntityToBeanMapper entityToBeanMapper = Mappers.getMapper(EntityToBeanMapper.class);

        List<Facility> facilityList = facilityEntityList.stream()
                .flatMap(facilityEntity -> Stream.of(entityToBeanMapper.facilityEntityToBean(facilityEntity)))
                .collect(Collectors.toList());
        return getFacilities(facilityList);
    }

    public List <Facility> getFacilities (List <Facility> facilityList) {
        List <Integer> bankIds = new ArrayList<>(
                facilityList.stream()
                        .flatMap(facility -> Stream.of(facility.getBankId()))
                        .collect(Collectors.toSet()));

        List <Integer> facilityIds = facilityList.stream()
                .flatMap(facility -> Stream.of(facility.getId()))
                .collect(Collectors.toList());

        List<CovenantEntity> covenantEntityList = covenantRepository.findCovenantEntity(facilityIds, bankIds);

        List <Bank> bankList = bankService.getBanks();
        Map<Integer, Bank> bankMap = getBankMap(bankList);
        mapBank(facilityList, bankMap);
        Map<Integer, Facility> facilityMap = getFacilityMap (facilityList);

        for (CovenantEntity covenant: covenantEntityList) {
            if (facilityMap.containsKey(covenant.getFacilityId())) {
                facilityMap.get(covenant.getFacilityId()).addBannedState(covenant.getBannedState());
                if (covenant.getMaxDefaultLikelihood() != null)
                    facilityMap.get(covenant.getFacilityId()).setMaxDefaultLikelihood(covenant.getMaxDefaultLikelihood());
            } else if (bankMap.containsKey(covenant.getBankId())) {
                bankMap.get(covenant.getBankId()).addBannedState(covenant.getBannedState());
                if (covenant.getMaxDefaultLikelihood() != null)
                    bankMap.get(covenant.getBankId()).setMaxDefaultLikelihood(covenant.getMaxDefaultLikelihood());
            }
        }

        return facilityList;
    }

    @Transactional
    public void load (InputStream inputStream) throws IOException {
        EntityToBeanMapper entityToBeanMapper = Mappers.getMapper(EntityToBeanMapper.class);
        List <Facility> facilityList = new FacilityUtil ()
                .load(inputStream);

        List <FacilityEntity> facilityEntityList = facilityList.stream()
                .flatMap(bankEntity -> Stream.of(entityToBeanMapper.beanToEntity(bankEntity)))
                .collect(Collectors.toList());

        saveAll(facilityEntityList);
    }

    private void saveAll(List<FacilityEntity> facilityEntityList) {
        List <Integer> facilityIds = facilityEntityList.stream()
                .flatMap(facilityEntity -> Stream.of(facilityEntity.getId()))
                .collect(Collectors.toList());

        facilityRepository.updateFacility(facilityIds);
        facilityRepository.saveAll(facilityEntityList);
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

    private void mapBank (List<Facility> facilityList, Map<Integer, Bank> bankMap) {
        for (Facility facility:facilityList) {
            facility.setBank(bankMap.get(facility.getBankId()));
        }
    }
}
