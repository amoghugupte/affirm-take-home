package com.amogh.affirm.ms.loan.csv.util;

import com.amogh.affirm.ms.common.model.Loan;
import com.amogh.affirm.ms.loan.csv.model.LoanCsvEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper
public interface CsvMapper {
    @Mappings({})
    Loan entityToBean(LoanCsvEntity source);


    @InheritInverseConfiguration
    LoanCsvEntity beanToEntity(Loan source);
}
