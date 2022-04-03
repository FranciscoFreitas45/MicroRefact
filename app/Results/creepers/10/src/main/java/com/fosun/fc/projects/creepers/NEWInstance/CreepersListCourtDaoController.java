package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CreepersListCourtDaoController {

 private CreepersListCourtDao creeperslistcourtdao;


@GetMapping
("/queryListByMerName")
public List<TCreepersListCourt> queryListByMerName(@RequestParam(name = "merName") String merName){
  return creeperslistcourtdao.queryListByMerName(merName);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return creeperslistcourtdao.findAll(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return creeperslistcourtdao.save(Object);
}


@PutMapping
("/updateListByMerName")
public void updateListByMerName(@RequestParam(name = "merName") String merName,@RequestParam(name = "flag") String flag){
creeperslistcourtdao.updateListByMerName(merName,flag);
}


}