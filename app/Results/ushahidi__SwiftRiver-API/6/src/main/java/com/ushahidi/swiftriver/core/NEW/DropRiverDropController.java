package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Drop;
@RestController
@CrossOrigin
public class DropRiverDropController {

@Autowired
 private DropRiverDropService dropriverdropservice;


@PutMapping
("/RiverDrop/{id}/Drop/setDrop")
public void setDrop(@PathVariable(name="id") long id2DXZ,@RequestBody Drop drop){
dropriverdropservice.setDrop(id2DXZ,drop);
}


@GetMapping
("/RiverDrop/{id}/Drop/getDrop")
public Drop getDrop(@PathVariable(name="id") long id2DXZ){
return dropriverdropservice.getDrop(id2DXZ);
}


}