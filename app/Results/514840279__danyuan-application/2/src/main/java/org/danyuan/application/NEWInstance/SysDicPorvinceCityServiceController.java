package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysDicPorvinceCityServiceController {

 private SysDicPorvinceCityService sysdicporvincecityservice;


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return sysdicporvincecityservice.findOne(Object);
}


}