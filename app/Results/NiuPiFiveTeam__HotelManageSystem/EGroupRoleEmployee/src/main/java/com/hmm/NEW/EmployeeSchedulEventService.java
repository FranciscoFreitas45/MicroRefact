package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.dao.EmployeeDao;
import com.hmm.employee.entity.Employee;
@Service
public class EmployeeSchedulEventService {

@Autowired
 private EmployeeDao employeedao;


public Employee getEmploy(Integer emp_idOBPL){
return employeedao.getEmploy(emp_idOBPL);
}


public void setEmploy(Integer emp_idOBPL,Employee employ){
employeedao.setEmploy(emp_idOBPL,employ);
}


}