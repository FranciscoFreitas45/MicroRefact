package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmployeePermissionServiceController {

 private EmployeePermissionService employeepermissionservice;


@GetMapping
("/getEmployeePermission")
public RolePermissionDTO getEmployeePermission(@RequestParam(name = "employeeId") Long employeeId){
  return employeepermissionservice.getEmployeePermission(employeeId);
}


@PutMapping
("/deleteEmployeeRole")
public void deleteEmployeeRole(@RequestParam(name = "employee") Employee employee){
employeepermissionservice.deleteEmployeeRole(employee);
}


@PutMapping
("/editEmployeeRole")
public void editEmployeeRole(@RequestParam(name = "employeeId") Long employeeId,@RequestParam(name = "roleIds") List<Long> roleIds){
employeepermissionservice.editEmployeeRole(employeeId,roleIds);
}


}