package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IDeptServiceController {

 private IDeptService ideptservice;


@GetMapping
("/findByDeptName")
public Department findByDeptName(@RequestParam(name = "deptName") String deptName){
  return ideptservice.findByDeptName(deptName);
}


}