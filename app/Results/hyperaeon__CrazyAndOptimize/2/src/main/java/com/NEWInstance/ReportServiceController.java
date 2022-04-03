package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ReportServiceController {

 private ReportService reportservice;


@GetMapping
("/getPersonCreditReport")
public Map<String,Object> getPersonCreditReport(@RequestParam(name = "login_name") String login_name){
  return reportservice.getPersonCreditReport(login_name);
}


@GetMapping
("/parseAndSave")
public boolean parseAndSave(@RequestParam(name = "content") String content,@RequestParam(name = "loginName") String loginName){
  return reportservice.parseAndSave(content,loginName);
}


}