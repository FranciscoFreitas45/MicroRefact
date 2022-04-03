package com.qidian.hcm.module.employee.repository;
 import com.qidian.hcm.module.employee.entity.EmployeeIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EmployeeIdentityRepository extends JpaRepository<EmployeeIdentity, Long>{


public void deleteAllByEmployeeId(Long employeeId)
;

public List<EmployeeIdentity> findAllByEmployeeId(Long id)
;

}