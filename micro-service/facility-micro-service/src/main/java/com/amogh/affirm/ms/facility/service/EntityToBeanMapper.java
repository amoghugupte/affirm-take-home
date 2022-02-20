package com.amogh.affirm.ms.facility.service;

import com.amogh.affirm.ms.common.model.*;
import com.amogh.affirm.ms.facility.entity.*;
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
    LoanAssignmentEntity beanToEntity(LoanAssignment source);

    @InheritInverseConfiguration
    YieldEntity beanToEntity(Yield source);
}
