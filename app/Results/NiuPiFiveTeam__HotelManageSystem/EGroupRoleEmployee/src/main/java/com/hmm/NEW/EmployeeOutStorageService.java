package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.dao.EmployeeDao;
import com.hmm.employee.entity.Employee;
@Service
public class EmployeeOutStorageService {

@Autowired
 private EmployeeDao employeedao;


public Employee getWorker(Integer emp_id1OFM){
return employeedao.getWorker(emp_id1OFM);
}


public void setWorker(Integer emp_id1OFM,Employee worker){
employeedao.setWorker(emp_id1OFM,worker);
}


}