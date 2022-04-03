package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.entity.TCreepersAssetHandle;
@RestController
@CrossOrigin
public class TCreepersAssetHandleTCreepersAccountBakController {

@Autowired
 private TCreepersAssetHandleTCreepersAccountBakService tcreepersassethandletcreepersaccountbakservice;


@PutMapping
("/TCreepersAccountBak/{id}/TCreepersAssetHandle/setTCreepersAssetHandles")
public void setTCreepersAssetHandles(@PathVariable(name="id") Long id,@RequestBody List<TCreepersAssetHandle> TCreepersAssetHandles){
tcreepersassethandletcreepersaccountbakservice.setTCreepersAssetHandles(id,TCreepersAssetHandles);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersAssetHandle/getTCreepersAssetHandles")
public List<TCreepersAssetHandle> getTCreepersAssetHandles(@PathVariable(name="id") Long id){
return tcreepersassethandletcreepersaccountbakservice.getTCreepersAssetHandles(id);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersAssetHandle/addTCreepersAssetHandle")
public TCreepersAssetHandle addTCreepersAssetHandle(@PathVariable(name="id") Long id,@RequestParam TCreepersAssetHandle TCreepersAssetHandle){
return tcreepersassethandletcreepersaccountbakservice.addTCreepersAssetHandle(id,TCreepersAssetHandle);
}


@GetMapping
("/TCreepersAccountBak/{id}/TCreepersAssetHandle/removeTCreepersAssetHandle")
public TCreepersAssetHandle removeTCreepersAssetHandle(@PathVariable(name="id") Long id,@RequestParam TCreepersAssetHandle TCreepersAssetHandle){
return tcreepersassethandletcreepersaccountbakservice.removeTCreepersAssetHandle(id,TCreepersAssetHandle);
}


}