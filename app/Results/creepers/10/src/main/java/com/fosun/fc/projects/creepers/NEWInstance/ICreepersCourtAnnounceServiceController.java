package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersCourtAnnounceServiceController {

 private ICreepersCourtAnnounceService icreeperscourtannounceservice;


@GetMapping
("/findByMerName")
public List<TCreepersCourtAnnounce> findByMerName(@RequestParam(name = "merName") String merName){
  return icreeperscourtannounceservice.findByMerName(merName);
}


}