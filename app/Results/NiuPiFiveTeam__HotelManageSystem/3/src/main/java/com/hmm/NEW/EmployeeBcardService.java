package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.dao.EmployeeDao;
import com.hmm.employee.entity.Employee;
@Service
public class EmployeeBcardService {

@Autowired
 private EmployeeDao employeedao;


public void setEmploy(Integer emp_idR9B4,Employee employ){
employeedao.setEmploy(emp_idR9B4,employ);
}


public Employee getEmploy(Integer emp_idR9B4){
return employeedao.getEmploy(emp_idR9B4);
}


}