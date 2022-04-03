package com.qidian.hcm.module.salary.repository;
 import com.qidian.hcm.module.salary.entity.EmployeeFinancial;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface EmployeeFinancialRepository extends JpaRepository<EmployeeFinancial, Long>{


public List<EmployeeFinancial> findBySocialSecurityPlanId(Long id)
;

public List<EmployeeFinancial> findByHousingFundPlanId(Long id)
;

public List<EmployeeFinancial> findByThresholdId(Long id)
;

public Optional<EmployeeFinancial> findByEmployeeId(Long employeeId)
;

}