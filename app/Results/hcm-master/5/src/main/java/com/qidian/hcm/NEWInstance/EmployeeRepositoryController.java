package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmployeeRepositoryController {

 private EmployeeRepository employeerepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return employeerepository.findById(Object);
}


@GetMapping
("/findAllById")
public Object findAllById(@RequestParam(name = "Object") Object Object){
  return employeerepository.findAllById(Object);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return employeerepository.findAll(Object);
}


@GetMapping
("/findByEmployeeNo")
public Optional<Employee> findByEmployeeNo(@RequestParam(name = "employeeNo") String employeeNo){
  return employeerepository.findByEmployeeNo(employeeNo);
}


}