package com.amogh.affirm.basic.csv.repository;

import com.amogh.affirm.basic.csv.entity.LoanEntity;
import com.amogh.affirm.basic.csv.parser.LoanParser;
import com.amogh.affirm.basic.bean.Loan;
import com.amogh.affirm.basic.readwrite.ReaderWriter;
import lombok.Builder;
import lombok.Data;
import org.mapstruct.factory.Mappers;

@Builder
@Data
public class LoanRepository {
    private ReaderWriter readerWriter = null;

    private LoanParser loanParser = null;
    private EntityToBeanMapper entityToBeanMapper = null;

    public LoanRepository initiate() {
        try {
            loanParser = LoanParser
                    .builder()
                    .fileName("loans.csv")
                    .readerWriter(readerWriter)
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
