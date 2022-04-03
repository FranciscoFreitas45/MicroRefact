package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CreepersGeneralDaoController {

 private CreepersGeneralDao creepersgeneraldao;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return creepersgeneraldao.save(Object);
}


}