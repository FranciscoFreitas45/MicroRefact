package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersPublicEnforcementServiceController {

 private ICreepersPublicEnforcementService icreeperspublicenforcementservice;


@GetMapping
("/findByRptNoForMap")
public Map<String,Object> findByRptNoForMap(@RequestParam(name = "rptNo") String rptNo){
  return icreeperspublicenforcementservice.findByRptNoForMap(rptNo);
}


}