package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.entity.Employee;
@RestController
@CrossOrigin
public class EmployeeRoomOrderController {

@Autowired
 private EmployeeRoomOrderService employeeroomorderservice;


@PutMapping
("/RoomOrder/{id}/Employee/setEmployee")
public void setEmployee(@PathVariable(name="id") Integer emp_idWJ6N,@RequestBody Employee employee){
employeeroomorderservice.setEmployee(emp_idWJ6N,employee);
}


@GetMapping
("/RoomOrder/{id}/Employee/getEmployee")
public Employee getEmployee(@PathVariable(name="id") Integer emp_idWJ6N){
return employeeroomorderservice.getEmployee(emp_idWJ6N);
}


}