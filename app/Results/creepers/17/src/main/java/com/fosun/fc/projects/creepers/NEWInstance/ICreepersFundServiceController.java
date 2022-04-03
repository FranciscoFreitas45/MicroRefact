package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersFundServiceController {

 private ICreepersFundService icreepersfundservice;


@GetMapping
("/findByLoginNameForMap")
public Map<String,Object> findByLoginNameForMap(@RequestParam(name = "loginName") String loginName){
  return icreepersfundservice.findByLoginNameForMap(loginName);
}


}