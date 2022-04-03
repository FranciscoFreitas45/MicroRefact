package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.dao.EmployeeDao;
import com.hmm.employee.entity.Employee;
@Service
public class EmployeeLeaveService {

@Autowired
 private EmployeeDao employeedao;


public void setEmploy(Integer emp_idB9PD,Employee employ){
employeedao.setEmploy(emp_idB9PD,employ);
}


public Employee getEmploy(Integer emp_idB9PD){
return employeedao.getEmploy(emp_idB9PD);
}


}