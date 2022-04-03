package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class Check_RecordServiceController {

 private Check_RecordService check_recordservice;


@GetMapping
("/findCreditCardApproval")
public List<Check_Record> findCreditCardApproval(@RequestParam(name = "reportId") Integer reportId){
  return check_recordservice.findCreditCardApproval(reportId);
}


@GetMapping
("/findPersonageQuery")
public List<Check_Record> findPersonageQuery(@RequestParam(name = "reportId") Integer reportId){
  return check_recordservice.findPersonageQuery(reportId);
}


@GetMapping
("/findLoanAfterManager")
public List<Check_Record> findLoanAfterManager(@RequestParam(name = "reportId") Integer reportId){
  return check_recordservice.findLoanAfterManager(reportId);
}


@GetMapping
("/findLoanApproval")
public List<Check_Record> findLoanApproval(@RequestParam(name = "reportId") Integer reportId){
  return check_recordservice.findLoanApproval(reportId);
}


}