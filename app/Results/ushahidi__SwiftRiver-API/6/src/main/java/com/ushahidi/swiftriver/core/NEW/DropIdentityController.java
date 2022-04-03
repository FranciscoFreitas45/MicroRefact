package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Drop;
@RestController
@CrossOrigin
public class DropIdentityController {

@Autowired
 private DropIdentityService dropidentityservice;


@PutMapping
("/Identity/{id}/Drop/setDrops")
public void setDrops(@PathVariable(name="id") long id,@RequestBody List<Drop> drops){
dropidentityservice.setDrops(id,drops);
}


@GetMapping
("/Identity/{id}/Drop/getDrops")
public List<Drop> getDrops(@PathVariable(name="id") long id){
return dropidentityservice.getDrops(id);
}


}