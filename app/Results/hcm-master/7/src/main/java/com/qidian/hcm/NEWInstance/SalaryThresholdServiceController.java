package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SalaryThresholdServiceController {

 private SalaryThresholdService salarythresholdservice;


@GetMapping
("/getSalaryThreshold")
public List<SalaryThresholdResponse> getSalaryThreshold(){
  return salarythresholdservice.getSalaryThreshold();
}


@PutMapping
("/updateThreshold")
public void updateThreshold(@RequestParam(name = "id") Long id,@RequestParam(name = "salaryThresholdRequest") SalaryThresholdRequest salaryThresholdRequest){
salarythresholdservice.updateThreshold(id,salaryThresholdRequest);
}


}