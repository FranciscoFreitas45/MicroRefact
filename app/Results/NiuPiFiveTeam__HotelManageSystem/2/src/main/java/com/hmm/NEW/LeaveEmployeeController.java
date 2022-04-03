package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.leave.entity.Leave;
@RestController
@CrossOrigin
public class LeaveEmployeeController {

@Autowired
 private LeaveEmployeeService leaveemployeeservice;


@PutMapping
("/Employee/{id}/Leave/setLeaves")
public void setLeaves(@PathVariable(name="id") Integer emp_id,@RequestBody Set<Leave> leaves){
leaveemployeeservice.setLeaves(emp_id,leaves);
}


@GetMapping
("/Employee/{id}/Leave/getLeaves")
public Set<Leave> getLeaves(@PathVariable(name="id") Integer emp_id){
return leaveemployeeservice.getLeaves(emp_id);
}


}