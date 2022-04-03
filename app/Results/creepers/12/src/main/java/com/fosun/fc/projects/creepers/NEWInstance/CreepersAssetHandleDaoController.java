package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CreepersAssetHandleDaoController {

 private CreepersAssetHandleDao creepersassethandledao;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return creepersassethandledao.save(Object);
}


}