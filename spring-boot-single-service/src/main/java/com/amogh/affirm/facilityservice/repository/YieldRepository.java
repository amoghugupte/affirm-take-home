package com.amogh.affirm.facilityservice.repository;

import com.amogh.affirm.facilityservice.entity.BankEntity;
import com.amogh.affirm.facilityservice.entity.YieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface YieldRepository extends JpaRepository<YieldEntity, Integer> {
    @Query("select new YieldEntity (b.facilityId, sum (b.expectedYield)) from YieldEntity b " +
            "where sysdate >= b.effectiveFrom and sysdate <= b.effectiveTo " +
            "group by b.facilityId")
    List<YieldEntity> getYield();
}
