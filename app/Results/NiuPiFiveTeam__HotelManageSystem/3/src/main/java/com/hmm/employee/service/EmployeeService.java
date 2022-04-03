package com.hmm.employee.service;
 import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import com.hmm.common.web.ExtAjaxResponse;
import com.hmm.employee.entity.Employee;
import com.hmm.employee.entity.EmployeeDTO;
import com.hmm.employee.entity.EmployeeQueryDTO;
public interface EmployeeService {


public void updatePassword(String password,String userName)
;

public List<EmployeeDTO> findByids(Integer[] ids)
;

public Employee findByUserName(String userName)
;

public Employee findByEmpNameAndEmpNo(String empName,String empNo)
;

public void save(Employee entity)
;

public long count(Specification<Employee> spec)
;

public void deleteAll(Integer[] ids)
;

public Employee findByEmpNo(String empNo)
;

public EmployeeDTO findDTOByID(Integer id)
;

public Page<EmployeeDTO> findAll(Specification<Employee> spec,Pageable pageable)
;

public ExtAjaxResponse ajaxUploadExcel(HttpServletRequest request,HttpServletResponse response)
;

public boolean existsById(Integer id)
;

public Employee findByEmpName(String empName)
;

public Optional<Employee> findById(Integer id)
;

public void deleteById(Integer id)
;

}