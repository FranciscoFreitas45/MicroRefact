package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WorkInfoServiceController {

 private WorkInfoService workinfoservice;


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return workinfoservice.insert(Object);
}


}