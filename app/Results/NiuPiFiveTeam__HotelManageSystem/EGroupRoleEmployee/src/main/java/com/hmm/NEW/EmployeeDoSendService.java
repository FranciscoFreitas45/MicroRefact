package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.dao.EmployeeDao;
import com.hmm.employee.entity.Employee;
@Service
public class EmployeeDoSendService {

@Autowired
 private EmployeeDao employeedao;


public void setSendWorker(Integer emp_idK9MW,Employee sendWorker){
employeedao.setSendWorker(emp_idK9MW,sendWorker);
}


public Employee getSendWorker(Integer emp_idK9MW){
return employeedao.getSendWorker(emp_idK9MW);
}


}