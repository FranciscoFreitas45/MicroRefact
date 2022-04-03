package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class Loan_Record_DetailServiceController {

 private Loan_Record_DetailService loan_record_detailservice;


@GetMapping
("/findCreditCardOverdue")
public List<Loan_Record_Detail> findCreditCardOverdue(@RequestParam(name = "reportId") Integer reportId){
  return loan_record_detailservice.findCreditCardOverdue(reportId);
}


@GetMapping
("/findCreditCardNoOverdue")
public List<Loan_Record_Detail> findCreditCardNoOverdue(@RequestParam(name = "reportId") Integer reportId){
  return loan_record_detailservice.findCreditCardNoOverdue(reportId);
}


@GetMapping
("/findCreditCardOverdueSixty")
public List<Loan_Record_Detail> findCreditCardOverdueSixty(@RequestParam(name = "reportId") Integer reportId){
  return loan_record_detailservice.findCreditCardOverdueSixty(reportId);
}


@GetMapping
("/findHouserLoadNoOverdue")
public List<Loan_Record_Detail> findHouserLoadNoOverdue(@RequestParam(name = "reportId") Integer reportId){
  return loan_record_detailservice.findHouserLoadNoOverdue(reportId);
}


@GetMapping
("/findHouserLoadOverdue")
public List<Loan_Record_Detail> findHouserLoadOverdue(@RequestParam(name = "reportId") Integer reportId){
  return loan_record_detailservice.findHouserLoadOverdue(reportId);
}


@GetMapping
("/findOtherLoadNoOverdue")
public List<Loan_Record_Detail> findOtherLoadNoOverdue(@RequestParam(name = "reportId") Integer reportId){
  return loan_record_detailservice.findOtherLoadNoOverdue(reportId);
}


@GetMapping
("/findOtherLoadOverdue")
public List<Loan_Record_Detail> findOtherLoadOverdue(@RequestParam(name = "reportId") Integer reportId){
  return loan_record_detailservice.findOtherLoadOverdue(reportId);
}


}