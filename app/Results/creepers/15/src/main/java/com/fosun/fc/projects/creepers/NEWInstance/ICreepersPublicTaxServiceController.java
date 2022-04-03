package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersPublicTaxServiceController {

 private ICreepersPublicTaxService icreeperspublictaxservice;


@GetMapping
("/findByRptNoForMap")
public Map<String,Object> findByRptNoForMap(@RequestParam(name = "rptNo") String rptNo){
  return icreeperspublictaxservice.findByRptNoForMap(rptNo);
}


}