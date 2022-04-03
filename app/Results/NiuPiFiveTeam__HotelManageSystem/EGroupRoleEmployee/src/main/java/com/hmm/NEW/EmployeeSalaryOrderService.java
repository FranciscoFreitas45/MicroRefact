package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.dao.EmployeeDao;
import com.hmm.employee.entity.Employee;
@Service
public class EmployeeSalaryOrderService {

@Autowired
 private EmployeeDao employeedao;


public void setEmployee(Integer emp_idQ9YD,Employee employee){
employeedao.setEmployee(emp_idQ9YD,employee);
}


public Employee getEmployee(Integer emp_idQ9YD){
return employeedao.getEmployee(emp_idQ9YD);
}


}