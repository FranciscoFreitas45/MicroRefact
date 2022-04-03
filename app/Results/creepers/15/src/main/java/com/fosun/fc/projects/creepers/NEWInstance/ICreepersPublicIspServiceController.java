package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersPublicIspServiceController {

 private ICreepersPublicIspService icreeperspublicispservice;


@GetMapping
("/findByRptNoForMap")
public Map<String,Object> findByRptNoForMap(@RequestParam(name = "rptNo") String rptNo){
  return icreeperspublicispservice.findByRptNoForMap(rptNo);
}


}