package com.qidian.hcm.module.employee.repository;
 import com.qidian.hcm.module.employee.entity.EmployeeEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EmployeeEducationRepository extends JpaRepository<EmployeeEducation, Long>{


public void deleteAllByEmployeeId(Long employeeId)
;

public List<EmployeeEducation> findAllByEmployeeId(Long id)
;

}