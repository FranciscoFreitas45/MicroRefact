package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.entity.TCreepersCcDetail;
@RestController
@CrossOrigin
public class TCreepersCcDetailTCreepersAccountBakController {

@Autowired
 private TCreepersCcDetailTCreepersAccountBakService tcreepersccdetailtcreepersaccountbakservice;


@PutMapping
("/TCreepersAccountBak/{id}/TCreepersCcDetail/setTCreepersCcDetails")
public void setTCreepersCcDetails(@PathVariable(name="id") Long id,@RequestBody List<TCreepersCcDetail> TCreepersCcDetails){
tcreepersccdetailtcreepersaccountbakservice.setTCreepersCcDetails(id,TCreepersCcDetails);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersCcDetail/removeTCreepersCcDetail")
public TCreepersCcDetail removeTCreepersCcDetail(@PathVariable(name="id") Long id,@RequestParam TCreepersCcDetail TCreepersCcDetail){
return tcreepersccdetailtcreepersaccountbakservice.removeTCreepersCcDetail(id,TCreepersCcDetail);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersCcDetail/getTCreepersCcDetails")
public List<TCreepersCcDetail> getTCreepersCcDetails(@PathVariable(name="id") Long id){
return tcreepersccdetailtcreepersaccountbakservice.getTCreepersCcDetails(id);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersCcDetail/addTCreepersCcDetail")
public TCreepersCcDetail addTCreepersCcDetail(@PathVariable(name="id") Long id,@RequestParam TCreepersCcDetail TCreepersCcDetail){
return tcreepersccdetailtcreepersaccountbakservice.addTCreepersCcDetail(id,TCreepersCcDetail);
}


}