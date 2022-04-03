package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.entity.TCreepersAccountBak;
@RestController
@CrossOrigin
public class TCreepersAccountBakTCreepersOlDetailController {

@Autowired
 private TCreepersAccountBakTCreepersOlDetailService tcreepersaccountbaktcreepersoldetailservice;


@PutMapping
("/TCreepersOlDetail/{id}/TCreepersAccountBak/setTCreepersAccountBak")
public void setTCreepersAccountBak(@PathVariable(name="id") Long id,@RequestBody TCreepersAccountBak TCreepersAccountBak){
tcreepersaccountbaktcreepersoldetailservice.setTCreepersAccountBak(id,TCreepersAccountBak);
}


@GetMapping
("/TCreepersOlDetail/{id}/TCreepersAccountBak/getTCreepersAccountBak")
public TCreepersAccountBak getTCreepersAccountBak(@PathVariable(name="id") Long id){
return tcreepersaccountbaktcreepersoldetailservice.getTCreepersAccountBak(id);
}


}