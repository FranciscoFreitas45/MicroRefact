package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.dao.EmployeeDao;
import com.hmm.employee.entity.Employee;
@Service
public class EmployeeRoomCleanRecordService {

@Autowired
 private EmployeeDao employeedao;


public void setRoomWorker(Integer emp_idU6JN,Employee roomWorker){
employeedao.setRoomWorker(emp_idU6JN,roomWorker);
}


public Employee getRoomWorker(Integer emp_idU6JN){
return employeedao.getRoomWorker(emp_idU6JN);
}


}