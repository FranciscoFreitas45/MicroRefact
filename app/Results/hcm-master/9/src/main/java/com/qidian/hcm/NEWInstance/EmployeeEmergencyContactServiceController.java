package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmployeeEmergencyContactServiceController {

 private EmployeeEmergencyContactService employeeemergencycontactservice;


@GetMapping
("/getEmergencyContactsDTOList")
public List<EmployeeEmergencyContactDTO> getEmergencyContactsDTOList(@RequestParam(name = "employeeId") Long employeeId){
  return employeeemergencycontactservice.getEmergencyContactsDTOList(employeeId);
}


@PutMapping
("/saveEmployeeEmergencyContact")
public void saveEmployeeEmergencyContact(@RequestParam(name = "employeeId") Long employeeId,@RequestParam(name = "employeeEmergencyContactDTOList") List<EmployeeEmergencyContactDTO> employeeEmergencyContactDTOList){
employeeemergencycontactservice.saveEmployeeEmergencyContact(employeeId,employeeEmergencyContactDTOList);
}


}