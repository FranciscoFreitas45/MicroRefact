package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Link;
@RestController
@CrossOrigin
public class LinkDropController {

@Autowired
 private LinkDropService linkdropservice;


@GetMapping
("/Drop/{id}/Link/getOriginalUrl")
public Link getOriginalUrl(@PathVariable(name="id") long idLCK8){
return linkdropservice.getOriginalUrl(idLCK8);
}


@PutMapping
("/Drop/{id}/Link/setOriginalUrl")
public void setOriginalUrl(@PathVariable(name="id") long idLCK8,@RequestBody Link originalUrl){
linkdropservice.setOriginalUrl(idLCK8,originalUrl);
}


}