package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.entity.TCreepersAccountBak;
@RestController
@CrossOrigin
public class TCreepersAccountBakTCreepersAssetHandleController {

@Autowired
 private TCreepersAccountBakTCreepersAssetHandleService tcreepersaccountbaktcreepersassethandleservice;


@PutMapping
("/TCreepersAssetHandle/{id}/TCreepersAccountBak/setTCreepersAccountBak")
public void setTCreepersAccountBak(@PathVariable(name="id") Long id,@RequestBody TCreepersAccountBak TCreepersAccountBak){
tcreepersaccountbaktcreepersassethandleservice.setTCreepersAccountBak(id,TCreepersAccountBak);
}


@GetMapping
("/TCreepersAssetHandle/{id}/TCreepersAccountBak/getTCreepersAccountBak")
public TCreepersAccountBak getTCreepersAccountBak(@PathVariable(name="id") Long id){
return tcreepersaccountbaktcreepersassethandleservice.getTCreepersAccountBak(id);
}


}