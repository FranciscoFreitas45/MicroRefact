package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.entity.TCreepersAccountBak;
@RestController
@CrossOrigin
public class TCreepersAccountBakTCreepersPublicSanctionController {

@Autowired
 private TCreepersAccountBakTCreepersPublicSanctionService tcreepersaccountbaktcreeperspublicsanctionservice;


@PutMapping
("/TCreepersPublicSanction/{id}/TCreepersAccountBak/setTCreepersAccountBak")
public void setTCreepersAccountBak(@PathVariable(name="id") Long id,@RequestBody TCreepersAccountBak TCreepersAccountBak){
tcreepersaccountbaktcreeperspublicsanctionservice.setTCreepersAccountBak(id,TCreepersAccountBak);
}


@GetMapping
("/TCreepersPublicSanction/{id}/TCreepersAccountBak/getTCreepersAccountBak")
public TCreepersAccountBak getTCreepersAccountBak(@PathVariable(name="id") Long id){
return tcreepersaccountbaktcreeperspublicsanctionservice.getTCreepersAccountBak(id);
}


}