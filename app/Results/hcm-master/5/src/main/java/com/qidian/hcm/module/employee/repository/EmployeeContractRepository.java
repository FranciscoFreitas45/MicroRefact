package com.qidian.hcm.module.employee.repository;
 import com.qidian.hcm.module.employee.entity.EmployeeContract;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EmployeeContractRepository extends JpaRepository<EmployeeContract, Long>{


public void deleteAllByEmployeeId(Long employeeId)
;

public List<EmployeeContract> findAllByEmployeeId(Long id)
;

}