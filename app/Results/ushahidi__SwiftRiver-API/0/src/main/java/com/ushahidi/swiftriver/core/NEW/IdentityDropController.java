package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Identity;
@RestController
@CrossOrigin
public class IdentityDropController {

@Autowired
 private IdentityDropService identitydropservice;


@PutMapping
("/Drop/{id}/Identity/setIdentity")
public void setIdentity(@PathVariable(name="id") long idYKYP,@RequestBody Identity identity){
identitydropservice.setIdentity(idYKYP,identity);
}


@GetMapping
("/Drop/{id}/Identity/getIdentity")
public Identity getIdentity(@PathVariable(name="id") long idYKYP){
return identitydropservice.getIdentity(idYKYP);
}


}