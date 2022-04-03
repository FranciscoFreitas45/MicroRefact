package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersPublicCivilServiceController {

 private ICreepersPublicCivilService icreeperspubliccivilservice;


@GetMapping
("/findByRptNoForMap")
public Map<String,Object> findByRptNoForMap(@RequestParam(name = "rptNo") String rptNo){
  return icreeperspubliccivilservice.findByRptNoForMap(rptNo);
}


}