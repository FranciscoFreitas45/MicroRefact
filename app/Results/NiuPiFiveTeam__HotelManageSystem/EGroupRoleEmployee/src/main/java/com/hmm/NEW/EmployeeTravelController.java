package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.entity.Employee;
@RestController
@CrossOrigin
public class EmployeeTravelController {

@Autowired
 private EmployeeTravelService employeetravelservice;


@PutMapping
("/Travel/{id}/Employee/setEmploy")
public void setEmploy(@PathVariable(name="id") Integer emp_idPBMQ,@RequestBody Employee employ){
employeetravelservice.setEmploy(emp_idPBMQ,employ);
}


@GetMapping
("/Travel/{id}/Employee/getEmploy")
public Employee getEmploy(@PathVariable(name="id") Integer emp_idPBMQ){
return employeetravelservice.getEmploy(emp_idPBMQ);
}


}