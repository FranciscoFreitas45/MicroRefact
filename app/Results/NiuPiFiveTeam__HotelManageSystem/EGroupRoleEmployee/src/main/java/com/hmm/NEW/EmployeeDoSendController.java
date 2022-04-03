package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.entity.Employee;
@RestController
@CrossOrigin
public class EmployeeDoSendController {

@Autowired
 private EmployeeDoSendService employeedosendservice;


@PutMapping
("/DoSend/{id}/Employee/setSendWorker")
public void setSendWorker(@PathVariable(name="id") Integer emp_idK9MW,@RequestBody Employee sendWorker){
employeedosendservice.setSendWorker(emp_idK9MW,sendWorker);
}


@GetMapping
("/DoSend/{id}/Employee/getSendWorker")
public Employee getSendWorker(@PathVariable(name="id") Integer emp_idK9MW){
return employeedosendservice.getSendWorker(emp_idK9MW);
}


}