package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.entity.Employee;
@RestController
@CrossOrigin
public class EmployeeSalaryOrderController {

@Autowired
 private EmployeeSalaryOrderService employeesalaryorderservice;


@PutMapping
("/SalaryOrder/{id}/Employee/setEmployee")
public void setEmployee(@PathVariable(name="id") Integer emp_idQ9YD,@RequestBody Employee employee){
employeesalaryorderservice.setEmployee(emp_idQ9YD,employee);
}


@GetMapping
("/SalaryOrder/{id}/Employee/getEmployee")
public Employee getEmployee(@PathVariable(name="id") Integer emp_idQ9YD){
return employeesalaryorderservice.getEmployee(emp_idQ9YD);
}


}