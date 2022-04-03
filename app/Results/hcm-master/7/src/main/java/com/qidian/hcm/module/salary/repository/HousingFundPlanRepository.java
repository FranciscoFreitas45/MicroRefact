package com.qidian.hcm.module.salary.repository;
 import com.qidian.hcm.module.salary.entity.HousingFundPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface HousingFundPlanRepository extends JpaRepository<HousingFundPlan, Long>{


public Optional<HousingFundPlan> findByNameAndIdNot(String name,Long id)
;

}