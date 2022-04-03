package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CreepersBasicDaoController {

 private CreepersBasicDao creepersbasicdao;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return creepersbasicdao.save(Object);
}


}