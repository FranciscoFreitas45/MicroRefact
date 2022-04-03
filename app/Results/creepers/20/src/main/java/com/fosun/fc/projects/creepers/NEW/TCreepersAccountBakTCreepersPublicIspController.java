package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.entity.TCreepersAccountBak;
@RestController
@CrossOrigin
public class TCreepersAccountBakTCreepersPublicIspController {

@Autowired
 private TCreepersAccountBakTCreepersPublicIspService tcreepersaccountbaktcreeperspublicispservice;


@PutMapping
("/TCreepersPublicIsp/{id}/TCreepersAccountBak/setTCreepersAccountBak")
public void setTCreepersAccountBak(@PathVariable(name="id") Long id,@RequestBody TCreepersAccountBak TCreepersAccountBak){
tcreepersaccountbaktcreeperspublicispservice.setTCreepersAccountBak(id,TCreepersAccountBak);
}


@GetMapping
("/TCreepersPublicIsp/{id}/TCreepersAccountBak/getTCreepersAccountBak")
public TCreepersAccountBak getTCreepersAccountBak(@PathVariable(name="id") Long id){
return tcreepersaccountbaktcreeperspublicispservice.getTCreepersAccountBak(id);
}


}