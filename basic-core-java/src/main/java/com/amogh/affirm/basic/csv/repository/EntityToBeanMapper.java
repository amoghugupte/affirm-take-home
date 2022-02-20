package com.amogh.affirm.basic.csv.repository;

import com.amogh.affirm.basic.bean.Bank;
import com.amogh.affirm.basic.bean.Facility;
import com.amogh.affirm.basic.csv.entity.BankEntity;
import com.amogh.affirm.basic.csv.entity.FacilityEntity;
import com.amogh.affirm.basic.csv.entity.LoanEntity;
import com.amogh.affirm.basic.bean.Loan;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper
public interface EntityToBeanMapper {
    @Mappings({})
    Bank entityToBean(BankEntity source);
    @Mappings({})
    Facility facilityEntityToBean(FacilityEntity source);
    @Mappings({})
    Loan entityToBean(LoanEntity source);


    @InheritInverseConfiguration
    BankEntity beanToEntity(Bank source);
    @InheritInverseConfiguration
    FacilityEntity beanToEntity(Facility source);
    @InheritInverseConfiguration
    LoanEntity beanToEntity(Loan source);
}
