package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CreepersListShixinDaoController {

 private CreepersListShixinDao creeperslistshixindao;


@GetMapping
("/queryListByMerName")
public List<TCreepersListShixin> queryListByMerName(@RequestParam(name = "merName") String merName){
  return creeperslistshixindao.queryListByMerName(merName);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return creeperslistshixindao.findAll(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return creeperslistshixindao.save(Object);
}


@PutMapping
("/updateListByMerName")
public void updateListByMerName(@RequestParam(name = "merName") String merName,@RequestParam(name = "flag") String flag){
creeperslistshixindao.updateListByMerName(merName,flag);
}


}