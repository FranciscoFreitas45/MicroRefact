package br.com.fatecmogidascruzes.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmployeeWebServiceController {

 private EmployeeWebService employeewebservice;


@GetMapping
("/getEnabledByCourse")
public List<EmployeeEntryDTO> getEnabledByCourse(@RequestParam(name = "course") Course course){
  return employeewebservice.getEnabledByCourse(course);
}


@GetMapping
("/getEnabledProfessors")
public List<EmployeeEntryDTO> getEnabledProfessors(){
  return employeewebservice.getEnabledProfessors();
}


}