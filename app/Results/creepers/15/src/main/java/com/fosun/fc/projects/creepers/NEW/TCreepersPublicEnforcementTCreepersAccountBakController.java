package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicEnforcement;
@RestController
@CrossOrigin
public class TCreepersPublicEnforcementTCreepersAccountBakController {

@Autowired
 private TCreepersPublicEnforcementTCreepersAccountBakService tcreeperspublicenforcementtcreepersaccountbakservice;


@PutMapping
("/TCreepersAccountBak/{id}/TCreepersPublicEnforcement/setTCreepersPublicEnforcements")
public void setTCreepersPublicEnforcements(@PathVariable(name="id") Long id,@RequestBody List<TCreepersPublicEnforcement> TCreepersPublicEnforcements){
tcreeperspublicenforcementtcreepersaccountbakservice.setTCreepersPublicEnforcements(id,TCreepersPublicEnforcements);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersPublicEnforcement/getTCreepersPublicEnforcements")
public List<TCreepersPublicEnforcement> getTCreepersPublicEnforcements(@PathVariable(name="id") Long id){
return tcreeperspublicenforcementtcreepersaccountbakservice.getTCreepersPublicEnforcements(id);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersPublicEnforcement/removeTCreepersPublicEnforcement")
public TCreepersPublicEnforcement removeTCreepersPublicEnforcement(@PathVariable(name="id") Long id,@RequestParam TCreepersPublicEnforcement TCreepersPublicEnforcement){
return tcreeperspublicenforcementtcreepersaccountbakservice.removeTCreepersPublicEnforcement(id,TCreepersPublicEnforcement);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersPublicEnforcement/addTCreepersPublicEnforcement")
public TCreepersPublicEnforcement addTCreepersPublicEnforcement(@PathVariable(name="id") Long id,@RequestParam TCreepersPublicEnforcement TCreepersPublicEnforcement){
return tcreeperspublicenforcementtcreepersaccountbakservice.addTCreepersPublicEnforcement(id,TCreepersPublicEnforcement);
}


}