package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersQueryLogServiceController {

 private ICreepersQueryLogService icreepersquerylogservice;


@GetMapping
("/findByRptNoForMap")
public Map<String,Object> findByRptNoForMap(@RequestParam(name = "rptNo") String rptNo){
  return icreepersquerylogservice.findByRptNoForMap(rptNo);
}


}