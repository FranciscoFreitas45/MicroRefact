package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TrainingDaoController {

 private TrainingDao trainingdao;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return trainingdao.findById(Object);
}


}