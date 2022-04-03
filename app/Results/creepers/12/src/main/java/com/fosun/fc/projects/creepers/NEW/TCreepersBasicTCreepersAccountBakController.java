package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.entity.TCreepersBasic;
@RestController
@CrossOrigin
public class TCreepersBasicTCreepersAccountBakController {

@Autowired
 private TCreepersBasicTCreepersAccountBakService tcreepersbasictcreepersaccountbakservice;


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersBasic/getTCreepersBasics")
public List<TCreepersBasic> getTCreepersBasics(@PathVariable(name="id") Long id){
return tcreepersbasictcreepersaccountbakservice.getTCreepersBasics(id);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersBasic/addTCreepersBasic")
public TCreepersBasic addTCreepersBasic(@PathVariable(name="id") Long id,@RequestParam TCreepersBasic TCreepersBasic){
return tcreepersbasictcreepersaccountbakservice.addTCreepersBasic(id,TCreepersBasic);
}


@PutMapping
("/TCreepersAccountBak/{id}/TCreepersBasic/setTCreepersBasics")
public void setTCreepersBasics(@PathVariable(name="id") Long id,@RequestBody List<TCreepersBasic> TCreepersBasics){
tcreepersbasictcreepersaccountbakservice.setTCreepersBasics(id,TCreepersBasics);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersBasic/removeTCreepersBasic")
public TCreepersBasic removeTCreepersBasic(@PathVariable(name="id") Long id,@RequestParam TCreepersBasic TCreepersBasic){
return tcreepersbasictcreepersaccountbakservice.removeTCreepersBasic(id,TCreepersBasic);
}


}