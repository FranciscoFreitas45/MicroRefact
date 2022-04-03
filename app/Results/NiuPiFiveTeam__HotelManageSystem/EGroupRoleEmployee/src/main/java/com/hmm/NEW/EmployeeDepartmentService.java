package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.dao.EmployeeDao;
import com.hmm.employee.entity.Employee;
@Service
public class EmployeeDepartmentService {

@Autowired
 private EmployeeDao employeedao;


public void setEmployee(Integer dept_id,Set<Employee> employ){
employeedao.setEmployee(dept_id,employ);
}


public Set<Employee> getEmployee(Integer dept_id){
return employeedao.getEmployee(dept_id);
}


}