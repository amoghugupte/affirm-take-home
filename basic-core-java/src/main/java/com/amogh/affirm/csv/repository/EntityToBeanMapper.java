package com.amogh.affirm.csv.repository;

import com.amogh.affirm.bean.Bank;
import com.amogh.affirm.bean.Facility;
import com.amogh.affirm.bean.Loan;
import com.amogh.affirm.csv.entity.BankEntity;
import com.amogh.affirm.csv.entity.FacilityEntity;
import com.amogh.affirm.csv.entity.LoanEntity;
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
