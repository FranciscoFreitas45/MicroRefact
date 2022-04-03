package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicIsp;
@RestController
@CrossOrigin
public class TCreepersPublicIspTCreepersAccountBakController {

@Autowired
 private TCreepersPublicIspTCreepersAccountBakService tcreeperspublicisptcreepersaccountbakservice;


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersPublicIsp/addTCreepersPublicIsp")
public TCreepersPublicIsp addTCreepersPublicIsp(@PathVariable(name="id") Long id,@RequestParam TCreepersPublicIsp TCreepersPublicIsp){
return tcreeperspublicisptcreepersaccountbakservice.addTCreepersPublicIsp(id,TCreepersPublicIsp);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersPublicIsp/getTCreepersPublicIsps")
public List<TCreepersPublicIsp> getTCreepersPublicIsps(@PathVariable(name="id") Long id){
return tcreeperspublicisptcreepersaccountbakservice.getTCreepersPublicIsps(id);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersPublicIsp/removeTCreepersPublicIsp")
public TCreepersPublicIsp removeTCreepersPublicIsp(@PathVariable(name="id") Long id,@RequestParam TCreepersPublicIsp TCreepersPublicIsp){
return tcreeperspublicisptcreepersaccountbakservice.removeTCreepersPublicIsp(id,TCreepersPublicIsp);
}


@PutMapping
("/TCreepersAccountBak/{id}/TCreepersPublicIsp/setTCreepersPublicIsps")
public void setTCreepersPublicIsps(@PathVariable(name="id") Long id,@RequestBody List<TCreepersPublicIsp> TCreepersPublicIsps){
tcreeperspublicisptcreepersaccountbakservice.setTCreepersPublicIsps(id,TCreepersPublicIsps);
}


}