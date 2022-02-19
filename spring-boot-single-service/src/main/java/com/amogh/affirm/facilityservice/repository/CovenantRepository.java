package com.amogh.affirm.facilityservice.repository;

import com.amogh.affirm.facilityservice.entity.CovenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CovenantRepository extends JpaRepository <CovenantEntity, Integer> {
    @Query("select c from CovenantEntity c where sysdate >= c.effectiveFrom and sysdate <= c.effectiveTo")
    List<CovenantEntity> findAll ();

    @Query("select c from CovenantEntity c where ((c.facilityId in :facilityIds or c.facilityId is null) or (c.bankId is null or c.bankId in :bankIds)) and sysdate >= c.effectiveFrom and sysdate <= c.effectiveTo")
    List<CovenantEntity> findCovenantEntity (List <Integer> facilityIds, List <Integer> bankIds);

    @Modifying
    @Query("update CovenantEntity b set b.effectiveTo = sysdate, b.updatedOn = sysdate where b.id in :covenantIds and sysdate >= b.effectiveFrom and sysdate <= b.effectiveTo")
    int updateCovenant (List<Integer> covenantIds);
}
