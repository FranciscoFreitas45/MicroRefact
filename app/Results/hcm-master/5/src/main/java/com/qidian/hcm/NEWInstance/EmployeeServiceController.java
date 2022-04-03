package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmployeeServiceController {

 private EmployeeService employeeservice;


@PutMapping
("/initCompanyAdmin")
public void initCompanyAdmin(@RequestParam(name = "request") InitCompanyAdminRequest request){
employeeservice.initCompanyAdmin(request);
}


@GetMapping
("/findByUserId")
public Employee findByUserId(@RequestParam(name = "userId") Long userId){
  return employeeservice.findByUserId(userId);
}


}