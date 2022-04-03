package com.qidian.hcm.module.employee.repository;
 import com.qidian.hcm.module.employee.dto.CommonListDTO;
import com.qidian.hcm.module.employee.entity.Employee;
import com.qidian.hcm.module.employee.enums.EmployeeStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee>{


public Page<Employee> findByStatusOrderByIdDesc(EmployeeStatus status,Pageable pageable)
;

public Optional<Employee> findByIdAndStatus(Long id,EmployeeStatus status)
;

public Optional<Employee> findByUserId(Long userId)
;

public List<Employee> findByIdNotIn(Collection<Long> ids)
;

public Page<Employee> findByNameLikeOrMobileLikeAndStatusNot(String name,String mobile,EmployeeStatus status,Pageable pageable)
;

public Optional<Employee> findByEmployeeNo(String employeeNo)
;

public Optional<Employee> findByIdAndStatusIsNot(Long id,EmployeeStatus status)
;

public List<Employee> findByEmployeeNoAndIdNot(String employeeNo,Long id)
;

public Page<Employee> findByNameLikeAndStatusOrderByIdDesc(String name,EmployeeStatus status,Pageable pageable)
;

@Query(value = "select new com.qidian.hcm.module.employee.dto." + "CommonListDTO(b.id,b.name) " + "from  Employee b where b.status <>'former' order by b.createdDate desc")
public List<CommonListDTO> findEmployees()
;

public Page<Employee> findByStatusNot(EmployeeStatus status,Pageable pageable)
;

}