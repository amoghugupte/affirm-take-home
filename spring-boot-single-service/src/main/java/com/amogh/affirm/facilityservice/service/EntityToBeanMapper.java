package com.amogh.affirm.facilityservice.service;

import com.amogh.affirm.facilityservice.entity.*;
import com.amogh.affirm.facilityservice.model.*;
import com.amogh.affirm.loanservice.entity.LoanEntity;
import com.amogh.affirm.loanservice.model.Loan;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper
public interface EntityToBeanMapper {
    @Mappings({})
    Bank entityToBean(BankEntity source);

    @Mappings({})
    Covenant entityToBean(CovenantEntity source);

    @Mappings({})
    Facility facilityEntityToBean(FacilityEntity source);

    @Mappings({})
    Loan entityToBean(LoanEntity source);

    @Mappings({})
    LoanAssignment entityToBean(LoanAssignmentEntity source);

    @Mappings({})
    Yield entityToBean(YieldEntity source);


    @InheritInverseConfiguration
    BankEntity beanToEntity(Bank source);

    @InheritInverseConfiguration
    CovenantEntity beanToEntity(Covenant source);

    @InheritInverseConfiguration
    FacilityEntity beanToEntity(Facility source);

    @InheritInverseConfiguration
    LoanEntity beanToEntity(Loan source);

    @InheritInverseConfiguration
    LoanAssignmentEntity beanToEntity(LoanAssignment source);

    @InheritInverseConfiguration
    YieldEntity beanToEntity(Yield source);
}
