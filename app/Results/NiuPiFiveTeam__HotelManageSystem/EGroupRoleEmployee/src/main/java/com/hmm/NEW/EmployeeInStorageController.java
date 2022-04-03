package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.entity.Employee;
@RestController
@CrossOrigin
public class EmployeeInStorageController {

@Autowired
 private EmployeeInStorageService employeeinstorageservice;


@PutMapping
("/InStorage/{id}/Employee/setEmployee")
public void setEmployee(@PathVariable(name="id") Integer emp_idIGL6,@RequestBody Employee employee){
employeeinstorageservice.setEmployee(emp_idIGL6,employee);
}


@GetMapping
("/InStorage/{id}/Employee/getEmployee")
public Employee getEmployee(@PathVariable(name="id") Integer emp_idIGL6){
return employeeinstorageservice.getEmployee(emp_idIGL6);
}


}