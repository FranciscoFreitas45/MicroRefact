package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicSanction;
@RestController
@CrossOrigin
public class TCreepersPublicSanctionTCreepersAccountBakController {

@Autowired
 private TCreepersPublicSanctionTCreepersAccountBakService tcreeperspublicsanctiontcreepersaccountbakservice;


@PutMapping
("/TCreepersAccountBak/{id}/TCreepersPublicSanction/setTCreepersPublicSanctions")
public void setTCreepersPublicSanctions(@PathVariable(name="id") Long id,@RequestBody List<TCreepersPublicSanction> TCreepersPublicSanctions){
tcreeperspublicsanctiontcreepersaccountbakservice.setTCreepersPublicSanctions(id,TCreepersPublicSanctions);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersPublicSanction/getTCreepersPublicSanctions")
public List<TCreepersPublicSanction> getTCreepersPublicSanctions(@PathVariable(name="id") Long id){
return tcreeperspublicsanctiontcreepersaccountbakservice.getTCreepersPublicSanctions(id);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersPublicSanction/removeTCreepersPublicSanction")
public TCreepersPublicSanction removeTCreepersPublicSanction(@PathVariable(name="id") Long id,@RequestParam TCreepersPublicSanction TCreepersPublicSanction){
return tcreeperspublicsanctiontcreepersaccountbakservice.removeTCreepersPublicSanction(id,TCreepersPublicSanction);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersPublicSanction/addTCreepersPublicSanction")
public TCreepersPublicSanction addTCreepersPublicSanction(@PathVariable(name="id") Long id,@RequestParam TCreepersPublicSanction TCreepersPublicSanction){
return tcreeperspublicsanctiontcreepersaccountbakservice.addTCreepersPublicSanction(id,TCreepersPublicSanction);
}


}