package com.amogh.affirm.ms.facility.csv.util;

import com.amogh.affirm.ms.common.model.*;
import com.amogh.affirm.ms.facility.csv.model.*;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper
public interface CsvMapper {
    @Mappings({})
    Bank entityToBean(BankCsvEntity source);

    @Mappings({})
    Covenant entityToBean(CovenantCsvEntity source);

    @Mappings({})
    Facility facilityEntityToBean(FacilityCsvEntity source);

    @Mappings({})
    LoanAssignment entityToBean(LoanAssignmentCsvEntity source);

    @Mappings({})
    Yield entityToBean(YieldCsvEntity source);

    @InheritInverseConfiguration
    BankCsvEntity beanToEntity(Bank source);

    @InheritInverseConfiguration
    CovenantCsvEntity beanToEntity(Covenant source);

    @InheritInverseConfiguration
    FacilityCsvEntity beanToEntity(Facility source);

    @InheritInverseConfiguration
    LoanAssignmentCsvEntity beanToEntity(LoanAssignment source);

    @InheritInverseConfiguration
    YieldCsvEntity beanToEntity(Yield source);
}
