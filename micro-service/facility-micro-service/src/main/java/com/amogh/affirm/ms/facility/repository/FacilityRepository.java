package com.amogh.affirm.ms.facility.repository;

import com.amogh.affirm.ms.facility.entity.FacilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface FacilityRepository extends JpaRepository <FacilityEntity, Integer> {
    @Query("select f from FacilityEntity f where sysdate >= f.effectiveFrom and sysdate <= f.effectiveTo")
     List<FacilityEntity> findAll ();

    @Query("select f from FacilityEntity f where f.id=:facilityId")
    List<FacilityEntity> findHistory (int facilityId);

    @Query("select f from FacilityEntity f where f.currAmount >= :amount and sysdate >= f.effectiveFrom and sysdate <= f.effectiveTo order by f.currAmount")
    List<FacilityEntity> findByCurrentAmount (BigDecimal amount);

    @Modifying
    @Query("update FacilityEntity f set f.effectiveTo = sysdate, f.updatedOn = sysdate  where f.id in :facilityIds and sysdate >= f.effectiveFrom and sysdate <= f.effectiveTo")
    int updateFacility (List<Integer> facilityIds);
}
