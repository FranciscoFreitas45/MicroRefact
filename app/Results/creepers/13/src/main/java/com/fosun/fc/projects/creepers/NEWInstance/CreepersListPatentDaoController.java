package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CreepersListPatentDaoController {

 private CreepersListPatentDao creeperslistpatentdao;


@GetMapping
("/queryListByMerName")
public List<TCreepersListPatent> queryListByMerName(@RequestParam(name = "merName") String merName){
  return creeperslistpatentdao.queryListByMerName(merName);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return creeperslistpatentdao.findAll(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return creeperslistpatentdao.save(Object);
}


@PutMapping
("/updateListByMerName")
public void updateListByMerName(@RequestParam(name = "merName") String merName,@RequestParam(name = "flag") String flag){
creeperslistpatentdao.updateListByMerName(merName,flag);
}


}