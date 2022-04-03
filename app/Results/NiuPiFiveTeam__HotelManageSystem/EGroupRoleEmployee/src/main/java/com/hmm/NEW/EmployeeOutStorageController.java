package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.entity.Employee;
@RestController
@CrossOrigin
public class EmployeeOutStorageController {

@Autowired
 private EmployeeOutStorageService employeeoutstorageservice;


@GetMapping
("/OutStorage/{id}/Employee/getWorker")
public Employee getWorker(@PathVariable(name="id") Integer emp_id1OFM){
return employeeoutstorageservice.getWorker(emp_id1OFM);
}


@PutMapping
("/OutStorage/{id}/Employee/setWorker")
public void setWorker(@PathVariable(name="id") Integer emp_id1OFM,@RequestBody Employee worker){
employeeoutstorageservice.setWorker(emp_id1OFM,worker);
}


}