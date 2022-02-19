package com.amogh.affirm.facilityservice.business;

import com.amogh.affirm.facilityservice.business.calculator.YieldCalculator;
import com.amogh.affirm.facilityservice.business.covenant.CovenantFactory;
import com.amogh.affirm.facilityservice.business.covenant.CovenantStrategy;
import com.amogh.affirm.facilityservice.csv.util.LoanAssignmentUtil;
import com.amogh.affirm.facilityservice.entity.LoanAssignmentEntity;
import com.amogh.affirm.facilityservice.model.Facility;
import com.amogh.affirm.facilityservice.model.LoanAssignment;
import com.amogh.affirm.facilityservice.model.Yield;
import com.amogh.affirm.facilityservice.repository.LoanAssignmentRepository;
import com.amogh.affirm.facilityservice.repository.YieldRepository;
import com.amogh.affirm.facilityservice.service.EntityToBeanMapper;
import com.amogh.affirm.facilityservice.service.FacilityService;
import com.amogh.affirm.loanservice.model.Loan;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LoanAssignmentService {
    @Autowired
    private LoanAssignmentRepository loanAssignmentRepository = null;

    @Autowired
    private YieldRepository yieldRepository = null;

    @Autowired
    private FacilityService facilityService = null;

    public void processLoan (Loan loan) {
        List<Facility> facilityList = facilityService.getFacilitiesByAmount(loan.getAmount());
        LoanAssignmentResult loanAssignmentResult = new LoanAssignmentResult();
        for (Facility facility: facilityList) {
            CovenantStrategy covenantStrategy = CovenantFactory.getCovenants(facility);
            if (covenantStrategy.canAssignFacility(loan)) {
                loanAssignmentResult.addFacility(facility);
            }
        }

        if (loanAssignmentResult.getBestFacility() == null) {
            //TODO Handle Default
            System.err.println(loan);
        } else {
            //todo update facility remainging amount
            EntityToBeanMapper entityToBeanMapper = Mappers.getMapper(EntityToBeanMapper.class);
            LoanAssignment loanAssignment = assignLoan (loan, loanAssignmentResult.getBestFacility());
            loanAssignmentRepository.save(entityToBeanMapper.beanToEntity(loanAssignment));

            YieldCalculator yieldCalculator = new YieldCalculator();
            Yield yield = yieldCalculator.calculate(loan, loanAssignmentResult.getBestFacility());
            yieldRepository.save (entityToBeanMapper.beanToEntity(yield));

            facilityService.save(loanAssignmentResult.getBestFacility());
        }
    }

    private LoanAssignment assignLoan(Loan loan, Facility bestFacility) {
        bestFacility.setCurrAmount(bestFacility.getCurrAmount().subtract(loan.getAmount()));
        return new LoanAssignment(loan.getId(), bestFacility.getId());
    }

    public List<LoanAssignment> getAssignment () {
        List <LoanAssignmentEntity> assignmentEntityList = loanAssignmentRepository.findAll();
        EntityToBeanMapper entityToBeanMapper = Mappers.getMapper(EntityToBeanMapper.class);
        return assignmentEntityList
                .stream()
                .flatMap(loanAssignmentEntity -> Stream.of(entityToBeanMapper.entityToBean(loanAssignmentEntity)))
                .collect(Collectors.toList());
    }

    public byte [] export () throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        List<LoanAssignment> loanAssignmentList = getAssignment();
        return new LoanAssignmentUtil ()
                .export(loanAssignmentList);
    }
}
