package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.entity.Employee;
@RestController
@CrossOrigin
public class EmployeeSchedulEventController {

@Autowired
 private EmployeeSchedulEventService employeescheduleventservice;


@GetMapping
("/SchedulEvent/{id}/Employee/getEmploy")
public Employee getEmploy(@PathVariable(name="id") Integer emp_idOBPL){
return employeescheduleventservice.getEmploy(emp_idOBPL);
}


@PutMapping
("/SchedulEvent/{id}/Employee/setEmploy")
public void setEmploy(@PathVariable(name="id") Integer emp_idOBPL,@RequestBody Employee employ){
employeescheduleventservice.setEmploy(emp_idOBPL,employ);
}


}