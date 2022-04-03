package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmployeeRoleRepositoryController {

 private EmployeeRoleRepository employeerolerepository;


@GetMapping
("/findAllByEmployeeId")
public List<EmployeeRole> findAllByEmployeeId(@RequestParam(name = "employeeId") Long employeeId){
  return employeerolerepository.findAllByEmployeeId(employeeId);
}


}