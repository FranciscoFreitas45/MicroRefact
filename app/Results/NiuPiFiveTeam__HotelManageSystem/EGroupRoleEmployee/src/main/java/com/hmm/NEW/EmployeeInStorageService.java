package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.dao.EmployeeDao;
import com.hmm.employee.entity.Employee;
@Service
public class EmployeeInStorageService {

@Autowired
 private EmployeeDao employeedao;


public void setEmployee(Integer emp_idIGL6,Employee employee){
employeedao.setEmployee(emp_idIGL6,employee);
}


public Employee getEmployee(Integer emp_idIGL6){
return employeedao.getEmployee(emp_idIGL6);
}


}