package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicCivil;
@RestController
@CrossOrigin
public class TCreepersPublicCivilTCreepersAccountBakController {

@Autowired
 private TCreepersPublicCivilTCreepersAccountBakService tcreeperspublicciviltcreepersaccountbakservice;


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersPublicCivil/addTCreepersPublicCivil")
public TCreepersPublicCivil addTCreepersPublicCivil(@PathVariable(name="id") Long id,@RequestParam TCreepersPublicCivil TCreepersPublicCivil){
return tcreeperspublicciviltcreepersaccountbakservice.addTCreepersPublicCivil(id,TCreepersPublicCivil);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersPublicCivil/removeTCreepersPublicCivil")
public TCreepersPublicCivil removeTCreepersPublicCivil(@PathVariable(name="id") Long id,@RequestParam TCreepersPublicCivil TCreepersPublicCivil){
return tcreeperspublicciviltcreepersaccountbakservice.removeTCreepersPublicCivil(id,TCreepersPublicCivil);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersPublicCivil/getTCreepersPublicCivils")
public List<TCreepersPublicCivil> getTCreepersPublicCivils(@PathVariable(name="id") Long id){
return tcreeperspublicciviltcreepersaccountbakservice.getTCreepersPublicCivils(id);
}


@PutMapping
("/TCreepersAccountBak/{id}/TCreepersPublicCivil/setTCreepersPublicCivils")
public void setTCreepersPublicCivils(@PathVariable(name="id") Long id,@RequestBody List<TCreepersPublicCivil> TCreepersPublicCivils){
tcreeperspublicciviltcreepersaccountbakservice.setTCreepersPublicCivils(id,TCreepersPublicCivils);
}


}