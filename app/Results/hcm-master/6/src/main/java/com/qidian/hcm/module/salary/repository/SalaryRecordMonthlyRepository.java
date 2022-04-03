package com.qidian.hcm.module.salary.repository;
 import com.qidian.hcm.module.salary.entity.SalaryRecordMonthly;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface SalaryRecordMonthlyRepository extends JpaRepository<SalaryRecordMonthly, Long>{


public Optional<SalaryRecordMonthly> findByCycle(String cycle)
;

}