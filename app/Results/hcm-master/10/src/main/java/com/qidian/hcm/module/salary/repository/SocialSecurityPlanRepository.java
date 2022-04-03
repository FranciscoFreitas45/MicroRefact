package com.qidian.hcm.module.salary.repository;
 import com.qidian.hcm.module.salary.entity.SocialSecurityPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface SocialSecurityPlanRepository extends JpaRepository<SocialSecurityPlan, Long>{


public Optional<SocialSecurityPlan> findByNameAndIdNot(String name,Long id)
;

}