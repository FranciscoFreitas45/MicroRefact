package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersInsuranceServiceController {

 private ICreepersInsuranceService icreepersinsuranceservice;


@PutMapping
("/processByParam")
public void processByParam(@RequestParam(name = "param") CreepersLoginParamDTO param){
icreepersinsuranceservice.processByParam(param);
}


@GetMapping
("/findListByCertNoForMap")
public Map<String,Object> findListByCertNoForMap(@RequestParam(name = "certNo") String certNo){
  return icreepersinsuranceservice.findListByCertNoForMap(certNo);
}


}