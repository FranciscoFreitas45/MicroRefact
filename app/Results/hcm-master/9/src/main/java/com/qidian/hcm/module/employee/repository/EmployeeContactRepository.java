package com.qidian.hcm.module.employee.repository;
 import com.qidian.hcm.module.employee.entity.EmployeeContact;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EmployeeContactRepository extends JpaRepository<EmployeeContact, Long>{


public void deleteAllByEmployeeId(Long employeeId)
;

public List<EmployeeContact> findAllByEmployeeId(Long id)
;

}