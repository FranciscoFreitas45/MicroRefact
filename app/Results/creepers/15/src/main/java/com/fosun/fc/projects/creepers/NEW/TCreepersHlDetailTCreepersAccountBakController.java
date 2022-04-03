package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.entity.TCreepersHlDetail;
@RestController
@CrossOrigin
public class TCreepersHlDetailTCreepersAccountBakController {

@Autowired
 private TCreepersHlDetailTCreepersAccountBakService tcreepershldetailtcreepersaccountbakservice;


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersHlDetail/addTCreepersHlDetail")
public TCreepersHlDetail addTCreepersHlDetail(@PathVariable(name="id") Long id,@RequestParam TCreepersHlDetail TCreepersHlDetail){
return tcreepershldetailtcreepersaccountbakservice.addTCreepersHlDetail(id,TCreepersHlDetail);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersHlDetail/getTCreepersHlDetails")
public List<TCreepersHlDetail> getTCreepersHlDetails(@PathVariable(name="id") Long id){
return tcreepershldetailtcreepersaccountbakservice.getTCreepersHlDetails(id);
}


@PutMapping
("/TCreepersAccountBak/{id}/TCreepersHlDetail/setTCreepersHlDetails")
public void setTCreepersHlDetails(@PathVariable(name="id") Long id,@RequestBody List<TCreepersHlDetail> TCreepersHlDetails){
tcreepershldetailtcreepersaccountbakservice.setTCreepersHlDetails(id,TCreepersHlDetails);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersHlDetail/removeTCreepersHlDetail")
public TCreepersHlDetail removeTCreepersHlDetail(@PathVariable(name="id") Long id,@RequestParam TCreepersHlDetail TCreepersHlDetail){
return tcreepershldetailtcreepersaccountbakservice.removeTCreepersHlDetail(id,TCreepersHlDetail);
}


}