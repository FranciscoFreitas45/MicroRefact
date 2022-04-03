package com.byr.warehouse.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ApplyOutServiceController {

 private ApplyOutService applyoutservice;


@GetMapping
("/getNumberOfTodayApplyEnter")
public int getNumberOfTodayApplyEnter(){
  return applyoutservice.getNumberOfTodayApplyEnter();
}


}