package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmployeeContactServiceController {

 private EmployeeContactService employeecontactservice;


@GetMapping
("/getEmployeeContactDTOList")
public List<EmployeeContactDTO> getEmployeeContactDTOList(@RequestParam(name = "employeeId") Long employeeId){
  return employeecontactservice.getEmployeeContactDTOList(employeeId);
}


@PutMapping
("/saveEmployeeContact")
public void saveEmployeeContact(@RequestParam(name = "employeeId") Long employeeId,@RequestParam(name = "employeeContactDTOS") List<EmployeeContactDTO> employeeContactDTOS){
employeecontactservice.saveEmployeeContact(employeeId,employeeContactDTOS);
}


}