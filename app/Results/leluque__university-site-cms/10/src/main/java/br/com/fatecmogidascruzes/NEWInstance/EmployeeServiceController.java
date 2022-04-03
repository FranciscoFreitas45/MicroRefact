package br.com.fatecmogidascruzes.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmployeeServiceController {

 private EmployeeService employeeservice;


@GetMapping
("/getEnabledByHash")
public Object getEnabledByHash(@RequestParam(name = "Object") Object Object){
  return employeeservice.getEnabledByHash(Object);
}


}