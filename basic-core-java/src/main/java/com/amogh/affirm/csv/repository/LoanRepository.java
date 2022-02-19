package com.amogh.affirm.csv.repository;

import com.amogh.affirm.bean.Loan;
import com.amogh.affirm.csv.entity.LoanEntity;
import com.amogh.affirm.csv.parser.LoanParser;
import lombok.Builder;
import lombok.Data;
import org.mapstruct.factory.Mappers;

@Builder
@Data
public class LoanRepository {
    private String folder = null;
    private LoanParser loanParser = null;
    private EntityToBeanMapper entityToBeanMapper = null;

    public LoanRepository initiate() {
        try {
            loanParser = LoanParser
                    .builder()
                    .fileName(folder + "/loans.csv")
                    .build()
                    .load();
            entityToBeanMapper = Mappers.getMapper(EntityToBeanMapper.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public Loan nextLoan () {
        LoanEntity loanEntity = loanParser.getNextLoan();
        if (loanEntity == null) {
            return null;
        } else {
            return entityToBeanMapper.entityToBean(loanEntity);
        }
    }
}
