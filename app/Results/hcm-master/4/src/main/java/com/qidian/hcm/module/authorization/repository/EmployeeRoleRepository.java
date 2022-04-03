package com.qidian.hcm.module.authorization.repository;
 import com.qidian.hcm.module.authorization.entity.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Long>{


public void deleteAllByEmployeeId(Long employeeId)
;

public List<EmployeeRole> findAllByEmployeeId(Long employeeId)
;

}