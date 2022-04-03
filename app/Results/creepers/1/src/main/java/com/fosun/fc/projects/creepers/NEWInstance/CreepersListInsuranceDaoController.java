package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CreepersListInsuranceDaoController {

 private CreepersListInsuranceDao creeperslistinsurancedao;


@GetMapping
("/queryListByUserCode")
public List<TCreepersListInsurance> queryListByUserCode(@RequestParam(name = "userCode") String userCode){
  return creeperslistinsurancedao.queryListByUserCode(userCode);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return creeperslistinsurancedao.findAll(Object);
}


@GetMapping
("/findTop1ByUserCode")
public TCreepersListInsurance findTop1ByUserCode(@RequestParam(name = "userCode") String userCode){
  return creeperslistinsurancedao.findTop1ByUserCode(userCode);
}


@PutMapping
("/updateListByUserCode")
public void updateListByUserCode(@RequestParam(name = "userCode") String userCode,@RequestParam(name = "flag") String flag){
creeperslistinsurancedao.updateListByUserCode(userCode,flag);
}


}