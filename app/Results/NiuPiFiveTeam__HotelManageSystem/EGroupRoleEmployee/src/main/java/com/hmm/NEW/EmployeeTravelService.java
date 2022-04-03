package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.dao.EmployeeDao;
import com.hmm.employee.entity.Employee;
@Service
public class EmployeeTravelService {

@Autowired
 private EmployeeDao employeedao;


public void setEmploy(Integer emp_idPBMQ,Employee employ){
employeedao.setEmploy(emp_idPBMQ,employ);
}


public Employee getEmploy(Integer emp_idPBMQ){
return employeedao.getEmploy(emp_idPBMQ);
}


}