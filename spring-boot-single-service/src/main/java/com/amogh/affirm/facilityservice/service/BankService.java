package com.amogh.affirm.facilityservice.service;

import com.amogh.affirm.facilityservice.csv.util.BankUtil;
import com.amogh.affirm.facilityservice.entity.BankEntity;
import com.amogh.affirm.facilityservice.model.Bank;
import com.amogh.affirm.facilityservice.repository.BankRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;

    public Bank getBank (Integer bankId) {
        EntityToBeanMapper entityToBeanMapper = Mappers.getMapper(EntityToBeanMapper.class);
        return entityToBeanMapper.entityToBean(bankRepository.findByBankId(bankId));
    }

    public void save (Bank bank) {
        EntityToBeanMapper entityToBeanMapper = Mappers.getMapper(EntityToBeanMapper.class);
        bankRepository.save(entityToBeanMapper.beanToEntity(bank));
    }

    public List<Bank> getBanks () {
        EntityToBeanMapper entityToBeanMapper = Mappers.getMapper(EntityToBeanMapper.class);
        List<BankEntity> bankEntityList = bankRepository.findAll();
        return bankEntityList.stream()
                .flatMap(bankEntity -> Stream.of(entityToBeanMapper.entityToBean(bankEntity)))
                .collect(Collectors.toList());
    }

    @Transactional
    public void load (InputStream file) throws IOException {
        List <Bank> bankList = new BankUtil().load(file);
        EntityToBeanMapper entityToBeanMapper = Mappers.getMapper(EntityToBeanMapper.class);
        List <BankEntity> bankEntityList = bankList.stream()
                .flatMap(bankEntity -> Stream.of(entityToBeanMapper.beanToEntity(bankEntity)))
                .collect(Collectors.toList());
        saveAll (bankEntityList);
    }

    private void saveAll(List<BankEntity> bankEntityList) {
        List <Integer> bankIds = bankEntityList.stream()
                .flatMap(bankEntity -> Stream.of(bankEntity.getId()))
                .collect(Collectors.toList());

        bankRepository.updateBank(bankIds);
        bankRepository.saveAll(bankEntityList);
    }
}
