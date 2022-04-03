package com.qidian.hcm.module.salary.repository;
 import com.qidian.hcm.module.salary.entity.SalaryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface SalaryHistoryRepository extends JpaRepository<SalaryHistory, Long>{


public List<SalaryHistory> findByEmployeeId(Long employeeId)
;

}