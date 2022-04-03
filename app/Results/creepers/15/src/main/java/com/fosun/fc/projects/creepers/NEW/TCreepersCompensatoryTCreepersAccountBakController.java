package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.entity.TCreepersCompensatory;
@RestController
@CrossOrigin
public class TCreepersCompensatoryTCreepersAccountBakController {

@Autowired
 private TCreepersCompensatoryTCreepersAccountBakService tcreeperscompensatorytcreepersaccountbakservice;


@PutMapping
("/TCreepersAccountBak/{id}/TCreepersCompensatory/setTCreepersCompensatories")
public void setTCreepersCompensatories(@PathVariable(name="id") Long id,@RequestBody List<TCreepersCompensatory> TCreepersCompensatories){
tcreeperscompensatorytcreepersaccountbakservice.setTCreepersCompensatories(id,TCreepersCompensatories);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersCompensatory/removeTCreepersCompensatory")
public TCreepersCompensatory removeTCreepersCompensatory(@PathVariable(name="id") Long id,@RequestParam TCreepersCompensatory TCreepersCompensatory){
return tcreeperscompensatorytcreepersaccountbakservice.removeTCreepersCompensatory(id,TCreepersCompensatory);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersCompensatory/addTCreepersCompensatory")
public TCreepersCompensatory addTCreepersCompensatory(@PathVariable(name="id") Long id,@RequestParam TCreepersCompensatory TCreepersCompensatory){
return tcreeperscompensatorytcreepersaccountbakservice.addTCreepersCompensatory(id,TCreepersCompensatory);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersCompensatory/getTCreepersCompensatories")
public List<TCreepersCompensatory> getTCreepersCompensatories(@PathVariable(name="id") Long id){
return tcreeperscompensatorytcreepersaccountbakservice.getTCreepersCompensatories(id);
}


}