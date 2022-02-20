package com.amogh.affirm.ms.facility.repository;

import com.amogh.affirm.ms.facility.entity.YieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface YieldRepository extends JpaRepository<YieldEntity, Integer> {
    @Query("select new YieldEntity (b.facilityId, sum (b.expectedYield)) from YieldEntity b " +
            "where sysdate >= b.effectiveFrom and sysdate <= b.effectiveTo " +
            "group by b.facilityId")
    List<YieldEntity> getYield();
}
