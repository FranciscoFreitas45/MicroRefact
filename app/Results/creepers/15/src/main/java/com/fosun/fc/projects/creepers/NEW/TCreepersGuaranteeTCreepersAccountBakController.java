package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.entity.TCreepersGuarantee;
@RestController
@CrossOrigin
public class TCreepersGuaranteeTCreepersAccountBakController {

@Autowired
 private TCreepersGuaranteeTCreepersAccountBakService tcreepersguaranteetcreepersaccountbakservice;


@PutMapping
("/TCreepersAccountBak/{id}/TCreepersGuarantee/setTCreepersGuarantees")
public void setTCreepersGuarantees(@PathVariable(name="id") Long id,@RequestBody List<TCreepersGuarantee> TCreepersGuarantees){
tcreepersguaranteetcreepersaccountbakservice.setTCreepersGuarantees(id,TCreepersGuarantees);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersGuarantee/addTCreepersGuarantee")
public TCreepersGuarantee addTCreepersGuarantee(@PathVariable(name="id") Long id,@RequestParam TCreepersGuarantee TCreepersGuarantee){
return tcreepersguaranteetcreepersaccountbakservice.addTCreepersGuarantee(id,TCreepersGuarantee);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersGuarantee/getTCreepersGuarantees")
public List<TCreepersGuarantee> getTCreepersGuarantees(@PathVariable(name="id") Long id){
return tcreepersguaranteetcreepersaccountbakservice.getTCreepersGuarantees(id);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersGuarantee/removeTCreepersGuarantee")
public TCreepersGuarantee removeTCreepersGuarantee(@PathVariable(name="id") Long id,@RequestParam TCreepersGuarantee TCreepersGuarantee){
return tcreepersguaranteetcreepersaccountbakservice.removeTCreepersGuarantee(id,TCreepersGuarantee);
}


}