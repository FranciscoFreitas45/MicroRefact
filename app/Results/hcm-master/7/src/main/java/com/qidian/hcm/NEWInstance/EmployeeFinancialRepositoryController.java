package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmployeeFinancialRepositoryController {

 private EmployeeFinancialRepository employeefinancialrepository;


@GetMapping
("/findBySocialSecurityPlanId")
public List<EmployeeFinancial> findBySocialSecurityPlanId(@RequestParam(name = "id") Long id){
  return employeefinancialrepository.findBySocialSecurityPlanId(id);
}


}