package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.entity.Employee;
@RestController
@CrossOrigin
public class EmployeeBcardController {

@Autowired
 private EmployeeBcardService employeebcardservice;


@PutMapping
("/Bcard/{id}/Employee/setEmploy")
public void setEmploy(@PathVariable(name="id") Integer emp_idR9B4,@RequestBody Employee employ){
employeebcardservice.setEmploy(emp_idR9B4,employ);
}


@GetMapping
("/Bcard/{id}/Employee/getEmploy")
public Employee getEmploy(@PathVariable(name="id") Integer emp_idR9B4){
return employeebcardservice.getEmploy(emp_idR9B4);
}


}