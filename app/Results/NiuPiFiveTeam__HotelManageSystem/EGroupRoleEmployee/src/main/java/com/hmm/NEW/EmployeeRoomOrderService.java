package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.dao.EmployeeDao;
import com.hmm.employee.entity.Employee;
@Service
public class EmployeeRoomOrderService {

@Autowired
 private EmployeeDao employeedao;


public void setEmployee(Integer emp_idWJ6N,Employee employee){
employeedao.setEmployee(emp_idWJ6N,employee);
}


public Employee getEmployee(Integer emp_idWJ6N){
return employeedao.getEmployee(emp_idWJ6N);
}


}