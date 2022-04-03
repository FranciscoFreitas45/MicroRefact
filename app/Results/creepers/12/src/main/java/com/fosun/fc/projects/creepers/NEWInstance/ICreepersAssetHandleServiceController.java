package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersAssetHandleServiceController {

 private ICreepersAssetHandleService icreepersassethandleservice;


@GetMapping
("/findByRptNoForMap")
public Map<String,Object> findByRptNoForMap(@RequestParam(name = "rptNo") String rptNo){
  return icreepersassethandleservice.findByRptNoForMap(rptNo);
}


}