package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.leave.dao.LeaveRepository;
import com.hmm.leave.entity.Leave;
@Service
public class LeaveEmployeeService {

@Autowired
 private LeaveRepository leaverepository;


public void setLeaves(Integer emp_id,Set<Leave> leaves){
leaverepository.setLeaves(emp_id,leaves);
}


public Set<Leave> getLeaves(Integer emp_id){
return leaverepository.getLeaves(emp_id);
}


}