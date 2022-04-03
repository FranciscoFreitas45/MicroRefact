package com.qidian.hcm.module.employee.repository;
 import com.qidian.hcm.module.employee.entity.EmployeeEmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EmployeeEmergencyContactRepository extends JpaRepository<EmployeeEmergencyContact, Long>{


public void deleteAllByEmployeeId(Long employeeId)
;

public List<EmployeeEmergencyContact> findAllByEmployeeId(Long id)
;

}