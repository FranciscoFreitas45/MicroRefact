package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmployeeWorkExperienceServiceController {

 private EmployeeWorkExperienceService employeeworkexperienceservice;


@GetMapping
("/getEmployeeWorkExperienceDTOList")
public List<EmployeeWorkExperienceDTO> getEmployeeWorkExperienceDTOList(@RequestParam(name = "employeeId") Long employeeId){
  return employeeworkexperienceservice.getEmployeeWorkExperienceDTOList(employeeId);
}


@PutMapping
("/saveEmployeeWorkExperience")
public void saveEmployeeWorkExperience(@RequestParam(name = "employeeId") Long employeeId,@RequestParam(name = "employeeWorkExperienceDTOList") List<EmployeeWorkExperienceDTO> employeeWorkExperienceDTOList){
employeeworkexperienceservice.saveEmployeeWorkExperience(employeeId,employeeWorkExperienceDTOList);
}


}