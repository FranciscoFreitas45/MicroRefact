package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Drop;
@RestController
@CrossOrigin
public class DropBucketDropController {

@Autowired
 private DropBucketDropService dropbucketdropservice;


@PutMapping
("/BucketDrop/{id}/Drop/setDrop")
public void setDrop(@PathVariable(name="id") long idINT8,@RequestBody Drop drop){
dropbucketdropservice.setDrop(idINT8,drop);
}


@GetMapping
("/BucketDrop/{id}/Drop/getDrop")
public Drop getDrop(@PathVariable(name="id") long idINT8){
return dropbucketdropservice.getDrop(idINT8);
}


}