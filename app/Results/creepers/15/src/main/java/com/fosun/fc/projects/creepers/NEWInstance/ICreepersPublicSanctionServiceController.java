package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersPublicSanctionServiceController {

 private ICreepersPublicSanctionService icreeperspublicsanctionservice;


@GetMapping
("/findByRptNoForMap")
public Map<String,Object> findByRptNoForMap(@RequestParam(name = "rptNo") String rptNo){
  return icreeperspublicsanctionservice.findByRptNoForMap(rptNo);
}


}