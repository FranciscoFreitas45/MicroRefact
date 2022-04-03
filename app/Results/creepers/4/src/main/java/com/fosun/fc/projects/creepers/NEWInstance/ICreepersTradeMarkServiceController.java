package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersTradeMarkServiceController {

 private ICreepersTradeMarkService icreeperstrademarkservice;


@GetMapping
("/findByMerName")
public List<TCreepersTradeMark> findByMerName(@RequestParam(name = "merName") String merName){
  return icreeperstrademarkservice.findByMerName(merName);
}


}