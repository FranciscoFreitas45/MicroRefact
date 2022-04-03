package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersTouristGuideServiceController {

 private ICreepersTouristGuideService icreeperstouristguideservice;


@GetMapping
("/findByParamForMap")
public Map<String,Object> findByParamForMap(@RequestParam(name = "param") CreepersLoginParamDTO param){
  return icreeperstouristguideservice.findByParamForMap(param);
}


}