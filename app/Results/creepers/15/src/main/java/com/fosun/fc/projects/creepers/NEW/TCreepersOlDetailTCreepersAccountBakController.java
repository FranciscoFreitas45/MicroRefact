package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.entity.TCreepersOlDetail;
@RestController
@CrossOrigin
public class TCreepersOlDetailTCreepersAccountBakController {

@Autowired
 private TCreepersOlDetailTCreepersAccountBakService tcreepersoldetailtcreepersaccountbakservice;


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersOlDetail/getTCreepersOlDetails")
public List<TCreepersOlDetail> getTCreepersOlDetails(@PathVariable(name="id") Long id){
return tcreepersoldetailtcreepersaccountbakservice.getTCreepersOlDetails(id);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersOlDetail/removeTCreepersOlDetail")
public TCreepersOlDetail removeTCreepersOlDetail(@PathVariable(name="id") Long id,@RequestParam TCreepersOlDetail TCreepersOlDetail){
return tcreepersoldetailtcreepersaccountbakservice.removeTCreepersOlDetail(id,TCreepersOlDetail);
}


@PutMapping
("/TCreepersAccountBak/{id}/TCreepersOlDetail/setTCreepersOlDetails")
public void setTCreepersOlDetails(@PathVariable(name="id") Long id,@RequestBody List<TCreepersOlDetail> TCreepersOlDetails){
tcreepersoldetailtcreepersaccountbakservice.setTCreepersOlDetails(id,TCreepersOlDetails);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersOlDetail/addTCreepersOlDetail")
public TCreepersOlDetail addTCreepersOlDetail(@PathVariable(name="id") Long id,@RequestParam TCreepersOlDetail TCreepersOlDetail){
return tcreepersoldetailtcreepersaccountbakservice.addTCreepersOlDetail(id,TCreepersOlDetail);
}


}