package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CompanyServiceController {

 private CompanyService companyservice;


@GetMapping
("/queryByCompanyInfo")
public List<Company> queryByCompanyInfo(){
  return companyservice.queryByCompanyInfo();
}


@GetMapping
("/queryAll")
public Object queryAll(@RequestParam(name = "Object") Object Object){
  return companyservice.queryAll(Object);
}


}