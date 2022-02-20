package com.amogh.affirm.ms.loan.service;

import com.amogh.affirm.ms.common.model.Loan;
import com.amogh.affirm.ms.loan.client.LoanAssignmentService;
import com.amogh.affirm.ms.loan.csv.util.LoanUtil;
import com.amogh.affirm.ms.loan.entity.LoanEntity;
import com.amogh.affirm.ms.loan.repository.LoanRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanAssignmentService loanAssignmentService;

    @Transactional
    public void load (InputStream inputStream) throws IOException {
        EntityToBeanMapper entityToBeanMapper = Mappers.getMapper(EntityToBeanMapper.class);
        List <Loan> loanList = new LoanUtil()
                .load(inputStream);

        List <LoanEntity> loanEntityList = loanList.stream()
                .flatMap(covenant -> Stream.of(entityToBeanMapper.beanToEntity(covenant)))
                .collect(Collectors.toList());

        saveAll(loanEntityList);
    }

    public void assign () {
        EntityToBeanMapper entityToBeanMapper = Mappers.getMapper(EntityToBeanMapper.class);
        List <LoanEntity> loanEntityList = loanRepository.findAll();
        List <Loan> loanList = loanEntityList
                .stream()
                .flatMap(loanEntity -> Stream.of(entityToBeanMapper.entityToBean(loanEntity)))
                .collect(Collectors.toList());

        loanList.stream().forEach(loan -> loanAssignmentService.processLoan(loan));
    }

    private void saveAll(List <LoanEntity> loanEntityList) {
        List <Integer> loanIds = loanEntityList.stream()
                .flatMap(facilityEntity -> Stream.of(facilityEntity.getId()))
                .collect(Collectors.toList());

        loanRepository.updateLoan(loanIds);
        loanRepository.saveAll(loanEntityList);
    }
}
