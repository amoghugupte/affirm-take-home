package com.amogh.affirm.ms.loan.repository;

import com.amogh.affirm.ms.loan.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanRepository extends JpaRepository<LoanEntity, Integer> {
    @Query("select b from LoanEntity b where sysdate >= b.effectiveFrom and sysdate <= b.effectiveTo order by b.id")
    List <LoanEntity> findAll ();

    @Modifying
    @Query("update LoanEntity l set l.effectiveTo = sysdate, l.updatedOn = sysdate where l.id in :loanIds and sysdate >= l.effectiveFrom and sysdate <= l.effectiveTo")
    int updateLoan (List<Integer> loanIds);
}
