package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CustomizedEmployeeFormRepositoryController {

 private CustomizedEmployeeFormRepository customizedemployeeformrepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return customizedemployeeformrepository.findById(Object);
}


}