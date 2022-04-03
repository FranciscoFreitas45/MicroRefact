package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersAccountBakServiceController {

 private ICreepersAccountBakService icreepersaccountbakservice;


@PutMapping
("/saveOrIfNotAccountBak")
public void saveOrIfNotAccountBak(@RequestParam(name = "entity") TCreepersAccountBak entity){
icreepersaccountbakservice.saveOrIfNotAccountBak(entity);
}


@GetMapping
("/findTop1ByUsrAndCde")
public TCreepersAccountBak findTop1ByUsrAndCde(@RequestParam(name = "usr") String usr,@RequestParam(name = "cde") String cde){
  return icreepersaccountbakservice.findTop1ByUsrAndCde(usr,cde);
}


@GetMapping
("/findByRptNoForMap")
public Map<String,Object> findByRptNoForMap(@RequestParam(name = "rptNo") String rptNo){
  return icreepersaccountbakservice.findByRptNoForMap(rptNo);
}


}