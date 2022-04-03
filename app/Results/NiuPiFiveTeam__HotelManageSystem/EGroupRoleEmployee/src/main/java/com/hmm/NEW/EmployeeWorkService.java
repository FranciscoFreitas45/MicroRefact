package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.dao.EmployeeDao;
import com.hmm.employee.entity.Employee;
@Service
public class EmployeeWorkService {

@Autowired
 private EmployeeDao employeedao;


public Employee getEmploy(Integer emp_idSDYK){
return employeedao.getEmploy(emp_idSDYK);
}


public void setEmploy(Integer emp_idSDYK,Employee employ){
employeedao.setEmploy(emp_idSDYK,employ);
}


}