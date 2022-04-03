package com.qidian.hcm.module.employee.repository;
 import com.qidian.hcm.module.employee.entity.EmployeeWorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EmployeeWorkExperienceRepository extends JpaRepository<EmployeeWorkExperience, Long>{


public void deleteAllByEmployeeId(Long employeeId)
;

public List<EmployeeWorkExperience> findAllByEmployeeId(Long id)
;

}