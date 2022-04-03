package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.RiverDrop;
@RestController
@CrossOrigin
public class RiverDropAccountController {

@Autowired
 private RiverDropAccountService riverdropaccountservice;


@GetMapping
("/Account/{id}/RiverDrop/getReadRiverDrops")
public List<RiverDrop> getReadRiverDrops(@PathVariable(name="id") long id){
return riverdropaccountservice.getReadRiverDrops(id);
}


@PutMapping
("/Account/{id}/RiverDrop/setReadRiverDrops")
public void setReadRiverDrops(@PathVariable(name="id") long id,@RequestBody List<RiverDrop> readRiverDrops){
riverdropaccountservice.setReadRiverDrops(id,readRiverDrops);
}


}