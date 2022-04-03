package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.entity.Employee;
@RestController
@CrossOrigin
public class EmployeeLeaveController {

@Autowired
 private EmployeeLeaveService employeeleaveservice;


@PutMapping
("/Leave/{id}/Employee/setEmploy")
public void setEmploy(@PathVariable(name="id") Integer emp_idB9PD,@RequestBody Employee employ){
employeeleaveservice.setEmploy(emp_idB9PD,employ);
}


@GetMapping
("/Leave/{id}/Employee/getEmploy")
public Employee getEmploy(@PathVariable(name="id") Integer emp_idB9PD){
return employeeleaveservice.getEmploy(emp_idB9PD);
}


}