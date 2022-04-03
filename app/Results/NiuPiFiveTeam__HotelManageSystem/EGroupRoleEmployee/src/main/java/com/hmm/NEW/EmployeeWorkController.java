package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.entity.Employee;
@RestController
@CrossOrigin
public class EmployeeWorkController {

@Autowired
 private EmployeeWorkService employeeworkservice;


@GetMapping
("/Work/{id}/Employee/getEmploy")
public Employee getEmploy(@PathVariable(name="id") Integer emp_idSDYK){
return employeeworkservice.getEmploy(emp_idSDYK);
}


@PutMapping
("/Work/{id}/Employee/setEmploy")
public void setEmploy(@PathVariable(name="id") Integer emp_idSDYK,@RequestBody Employee employ){
employeeworkservice.setEmploy(emp_idSDYK,employ);
}


}