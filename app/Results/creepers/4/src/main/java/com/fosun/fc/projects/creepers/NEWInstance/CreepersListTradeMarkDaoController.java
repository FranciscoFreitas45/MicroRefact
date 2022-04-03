package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CreepersListTradeMarkDaoController {

 private CreepersListTradeMarkDao creeperslisttrademarkdao;


@GetMapping
("/queryListByMerName")
public List<TCreepersListTradeMark> queryListByMerName(@RequestParam(name = "merName") String merName){
  return creeperslisttrademarkdao.queryListByMerName(merName);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return creeperslisttrademarkdao.findAll(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return creeperslisttrademarkdao.save(Object);
}


@PutMapping
("/updateListByMerName")
public void updateListByMerName(@RequestParam(name = "merName") String merName,@RequestParam(name = "flag") String flag){
creeperslisttrademarkdao.updateListByMerName(merName,flag);
}


}