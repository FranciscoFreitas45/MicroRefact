package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersTouristBlackListServiceController {

 private ICreepersTouristBlackListService icreeperstouristblacklistservice;


@GetMapping
("/findListByAgentNameAndType")
public List<TCreepersTourBlackList> findListByAgentNameAndType(@RequestParam(name = "merName") String merName){
  return icreeperstouristblacklistservice.findListByAgentNameAndType(merName);
}


@GetMapping
("/findListByGuideNo")
public List<TCreepersTourBlackList> findListByGuideNo(@RequestParam(name = "merName") String merName){
  return icreeperstouristblacklistservice.findListByGuideNo(merName);
}


}