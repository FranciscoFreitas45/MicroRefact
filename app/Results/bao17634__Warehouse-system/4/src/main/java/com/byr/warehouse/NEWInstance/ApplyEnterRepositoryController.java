package com.byr.warehouse.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ApplyEnterRepositoryController {

 private ApplyEnterRepository applyenterrepository;


@GetMapping
("/getYestdayApplys")
public List<ApplyEnter> getYestdayApplys(){
  return applyenterrepository.getYestdayApplys();
}


}