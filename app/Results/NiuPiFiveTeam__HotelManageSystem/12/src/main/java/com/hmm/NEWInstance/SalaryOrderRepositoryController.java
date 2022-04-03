package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SalaryOrderRepositoryController {

 private SalaryOrderRepository salaryorderrepository;


@GetMapping
("/findSalaryByDay")
public Float findSalaryByDay(@RequestParam(name = "date") String date){
  return salaryorderrepository.findSalaryByDay(date);
}


}