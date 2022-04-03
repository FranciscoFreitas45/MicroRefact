package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AreaServiceController {

 private AreaService areaservice;


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return areaservice.find(Object);
}


}