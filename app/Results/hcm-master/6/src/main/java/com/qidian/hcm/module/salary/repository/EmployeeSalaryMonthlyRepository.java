package com.qidian.hcm.module.salary.repository;
 import com.qidian.hcm.module.salary.dto.EmployeeSalaryMonthlyDTO;
import com.qidian.hcm.module.salary.entity.EmployeeSalaryMonthly;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
public interface EmployeeSalaryMonthlyRepository extends JpaSpecificationExecutor<EmployeeSalaryMonthly>, JpaRepository<EmployeeSalaryMonthly, Long>{


public List<EmployeeSalaryMonthly> findByEmployeeIdInAndDate(Collection<Long> ids,String date)
;

@Query(value = "select new com.qidian.hcm.module.salary.dto.EmployeeSalaryMonthlyDTO(" + "esm.employeeId,e.employeeNo,e.name,esm.include,esm.itemsResult) from EmployeeSalaryMonthly  esm," + "com.qidian.hcm.module.employee.entity.Employee e where esm.employeeId=e.id")
public List<EmployeeSalaryMonthlyDTO> findAllByDate(String date)
;

public List<EmployeeSalaryMonthly> findByEmployeeIdInAndDateAndIncludeIsTrue(List<Long> ids,String date)
;

public Optional<EmployeeSalaryMonthly> findByEmployeeIdAndDate(Long employeeId,String date)
;

}