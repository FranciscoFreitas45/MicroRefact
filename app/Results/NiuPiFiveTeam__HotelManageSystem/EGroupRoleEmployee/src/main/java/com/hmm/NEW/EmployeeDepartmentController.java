package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.entity.Employee;
@RestController
@CrossOrigin
public class EmployeeDepartmentController {

@Autowired
 private EmployeeDepartmentService employeedepartmentservice;


@PutMapping
("/Department/{id}/Employee/setEmployee")
public void setEmployee(@PathVariable(name="id") Integer dept_id,@RequestBody Set<Employee> employ){
employeedepartmentservice.setEmployee(dept_id,employ);
}


@GetMapping
("/Department/{id}/Employee/getEmployee")
public Set<Employee> getEmployee(@PathVariable(name="id") Integer dept_id){
return employeedepartmentservice.getEmployee(dept_id);
}


}