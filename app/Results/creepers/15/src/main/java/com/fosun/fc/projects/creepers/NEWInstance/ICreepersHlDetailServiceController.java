package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersHlDetailServiceController {

 private ICreepersHlDetailService icreepershldetailservice;


@GetMapping
("/findByRptNoForMap")
public Map<String,Object> findByRptNoForMap(@RequestParam(name = "rptNo") String rptNo){
  return icreepershldetailservice.findByRptNoForMap(rptNo);
}


}