package com.amogh.affirm.ms.loan.service;

import com.amogh.affirm.ms.common.model.Loan;
import com.amogh.affirm.ms.loan.entity.LoanEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper
public interface EntityToBeanMapper {

    @Mappings({})
    Loan entityToBean(LoanEntity source);

    @InheritInverseConfiguration
    LoanEntity beanToEntity(Loan source);
}
