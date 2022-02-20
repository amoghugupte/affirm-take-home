package com.amogh.affirm.ms.facility.repository;

import com.amogh.affirm.ms.facility.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankRepository extends JpaRepository<BankEntity, Integer> {
    @Query("select b from BankEntity b where sysdate >= b.effectiveFrom and sysdate <= b.effectiveTo")
    List <BankEntity> findAll ();

    @Query("select b from BankEntity b where b.id = :id and sysdate >= b.effectiveFrom and sysdate <= b.effectiveTo")
    BankEntity findByBankId (Integer id);

    @Modifying
    @Query("update BankEntity b set b.effectiveTo = sysdate, b.updatedOn = sysdate where b.id in :bankIds and sysdate >= b.effectiveFrom and sysdate <= b.effectiveTo")
    int updateBank (List<Integer> bankIds);
}
