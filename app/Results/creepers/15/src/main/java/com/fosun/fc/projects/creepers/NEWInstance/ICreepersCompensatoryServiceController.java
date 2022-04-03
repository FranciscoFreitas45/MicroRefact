package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersCompensatoryServiceController {

 private ICreepersCompensatoryService icreeperscompensatoryservice;


@GetMapping
("/findByRptNoForMap")
public Map<String,Object> findByRptNoForMap(@RequestParam(name = "rptNo") String rptNo){
  return icreeperscompensatoryservice.findByRptNoForMap(rptNo);
}


}