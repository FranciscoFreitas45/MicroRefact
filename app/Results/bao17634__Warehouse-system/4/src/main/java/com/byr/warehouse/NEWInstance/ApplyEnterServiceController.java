package com.byr.warehouse.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ApplyEnterServiceController {

 private ApplyEnterService applyenterservice;


@GetMapping
("/getNumberOfTodayApplyEnter")
public int getNumberOfTodayApplyEnter(){
  return applyenterservice.getNumberOfTodayApplyEnter();
}


}