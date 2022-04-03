package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CreepersListFundDaoController {

 private CreepersListFundDao creeperslistfunddao;


@GetMapping
("/queryListByUserCode")
public List<TCreepersListFund> queryListByUserCode(@RequestParam(name = "userCode") String userCode){
  return creeperslistfunddao.queryListByUserCode(userCode);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return creeperslistfunddao.findAll(Object);
}


@GetMapping
("/findTop1ByUserCode")
public TCreepersListFund findTop1ByUserCode(@RequestParam(name = "userCode") String userCode){
  return creeperslistfunddao.findTop1ByUserCode(userCode);
}


@PutMapping
("/updateListByUserCode")
public void updateListByUserCode(@RequestParam(name = "userCode") String userCode,@RequestParam(name = "flag") String flag){
creeperslistfunddao.updateListByUserCode(userCode,flag);
}


}