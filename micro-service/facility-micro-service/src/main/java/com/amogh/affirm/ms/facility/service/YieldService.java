package com.amogh.affirm.ms.facility.service;

import com.amogh.affirm.ms.common.model.Yield;
import com.amogh.affirm.ms.facility.csv.util.YieldUtil;
import com.amogh.affirm.ms.facility.entity.YieldEntity;
import com.amogh.affirm.ms.facility.repository.YieldRepository;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class YieldService {
    @Autowired
    private YieldRepository yieldRepository;

    public List<Yield> getYield () {
        EntityToBeanMapper entityToBeanMapper = Mappers.getMapper(EntityToBeanMapper.class);
        List<YieldEntity> yieldEntityList = yieldRepository.getYield ();
        return yieldEntityList.stream()
                .flatMap(yieldEntity -> Stream.of(entityToBeanMapper.entityToBean(yieldEntity)))
                .collect(Collectors.toList());
    }

    public byte [] export () throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        List<Yield> yieldList = getYield();
        return new YieldUtil().export(yieldList);
    }
}
