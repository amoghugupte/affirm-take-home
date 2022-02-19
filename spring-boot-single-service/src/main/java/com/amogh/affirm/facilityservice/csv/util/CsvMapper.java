package com.amogh.affirm.facilityservice.csv.util;

import com.amogh.affirm.facilityservice.csv.model.*;
import com.amogh.affirm.facilityservice.model.*;
import com.amogh.affirm.loanservice.csv.model.LoanCsvEntity;
import com.amogh.affirm.loanservice.model.Loan;
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
    Loan entityToBean(LoanCsvEntity source);

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
    LoanCsvEntity beanToEntity(Loan source);

    @InheritInverseConfiguration
    LoanAssignmentCsvEntity beanToEntity(LoanAssignment source);

    @InheritInverseConfiguration
    YieldCsvEntity beanToEntity(Yield source);
}
