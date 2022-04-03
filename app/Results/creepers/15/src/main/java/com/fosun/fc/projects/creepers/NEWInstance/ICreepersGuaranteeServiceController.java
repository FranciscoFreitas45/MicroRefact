package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersGuaranteeServiceController {

 private ICreepersGuaranteeService icreepersguaranteeservice;


@GetMapping
("/findByRptNoForMap")
public Map<String,Object> findByRptNoForMap(@RequestParam(name = "rptNo") String rptNo){
  return icreepersguaranteeservice.findByRptNoForMap(rptNo);
}


}