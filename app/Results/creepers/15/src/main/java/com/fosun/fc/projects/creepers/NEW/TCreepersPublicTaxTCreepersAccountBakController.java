package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicTax;
@RestController
@CrossOrigin
public class TCreepersPublicTaxTCreepersAccountBakController {

@Autowired
 private TCreepersPublicTaxTCreepersAccountBakService tcreeperspublictaxtcreepersaccountbakservice;


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersPublicTax/getTCreepersPublicTaxs")
public List<TCreepersPublicTax> getTCreepersPublicTaxs(@PathVariable(name="id") Long id){
return tcreeperspublictaxtcreepersaccountbakservice.getTCreepersPublicTaxs(id);
}


@PutMapping
("/TCreepersAccountBak/{id}/TCreepersPublicTax/setTCreepersPublicTaxs")
public void setTCreepersPublicTaxs(@PathVariable(name="id") Long id,@RequestBody List<TCreepersPublicTax> TCreepersPublicTaxs){
tcreeperspublictaxtcreepersaccountbakservice.setTCreepersPublicTaxs(id,TCreepersPublicTaxs);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersPublicTax/addTCreepersPublicTax")
public TCreepersPublicTax addTCreepersPublicTax(@PathVariable(name="id") Long id,@RequestParam TCreepersPublicTax TCreepersPublicTax){
return tcreeperspublictaxtcreepersaccountbakservice.addTCreepersPublicTax(id,TCreepersPublicTax);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersPublicTax/removeTCreepersPublicTax")
public TCreepersPublicTax removeTCreepersPublicTax(@PathVariable(name="id") Long id,@RequestParam TCreepersPublicTax TCreepersPublicTax){
return tcreeperspublictaxtcreepersaccountbakservice.removeTCreepersPublicTax(id,TCreepersPublicTax);
}


}