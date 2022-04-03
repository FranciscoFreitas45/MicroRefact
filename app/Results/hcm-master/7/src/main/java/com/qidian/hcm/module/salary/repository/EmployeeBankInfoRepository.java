package com.qidian.hcm.module.salary.repository;
 import com.qidian.hcm.module.salary.entity.EmployeeBankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface EmployeeBankInfoRepository extends JpaRepository<EmployeeBankInfo, Long>{


public Optional<EmployeeBankInfo> findByEmployeeId(Long employeeId)
;

}