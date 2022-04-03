package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CreepersListJudgementDaoController {

 private CreepersListJudgementDao creeperslistjudgementdao;


@GetMapping
("/queryListByMerName")
public List<TCreepersListJudgement> queryListByMerName(@RequestParam(name = "merName") String merName){
  return creeperslistjudgementdao.queryListByMerName(merName);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return creeperslistjudgementdao.findAll(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return creeperslistjudgementdao.save(Object);
}


@PutMapping
("/updateListByMerName")
public void updateListByMerName(@RequestParam(name = "merName") String merName,@RequestParam(name = "flag") String flag){
creeperslistjudgementdao.updateListByMerName(merName,flag);
}


}