package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.entity.TCreepersAccountBak;
@RestController
@CrossOrigin
public class TCreepersAccountBakTCreepersHlDetailController {

@Autowired
 private TCreepersAccountBakTCreepersHlDetailService tcreepersaccountbaktcreepershldetailservice;


@PutMapping
("/TCreepersHlDetail/{id}/TCreepersAccountBak/setTCreepersAccountBak")
public void setTCreepersAccountBak(@PathVariable(name="id") Long id,@RequestBody TCreepersAccountBak TCreepersAccountBak){
tcreepersaccountbaktcreepershldetailservice.setTCreepersAccountBak(id,TCreepersAccountBak);
}


@GetMapping
("/TCreepersHlDetail/{id}/TCreepersAccountBak/getTCreepersAccountBak")
public TCreepersAccountBak getTCreepersAccountBak(@PathVariable(name="id") Long id){
return tcreepersaccountbaktcreepershldetailservice.getTCreepersAccountBak(id);
}


}