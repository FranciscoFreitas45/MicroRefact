package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmployeeServiceController {

 private EmployeeService employeeservice;


@GetMapping
("/findByUserName")
public Employee findByUserName(@RequestParam(name = "userName") String userName){
  return employeeservice.findByUserName(userName);
}


@GetMapping
("/findByEmpNo")
public Employee findByEmpNo(@RequestParam(name = "empNo") String empNo){
  return employeeservice.findByEmpNo(empNo);
}


@GetMapping
("/findByEmpNameAndEmpNo")
public Employee findByEmpNameAndEmpNo(@RequestParam(name = "empName") String empName,@RequestParam(name = "empNo") String empNo){
  return employeeservice.findByEmpNameAndEmpNo(empName,empNo);
}


@PutMapping
("/save")
public void save(@RequestParam(name = "entity") Employee entity){
employeeservice.save(entity);
}


@GetMapping
("/findByEmpName")
public Employee findByEmpName(@RequestParam(name = "empName") String empName){
  return employeeservice.findByEmpName(empName);
}


}