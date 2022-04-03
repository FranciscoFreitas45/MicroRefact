package org.live.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.live.entity.MobileUser;
@RestController
@CrossOrigin
public class MobileUserApplyAnchorController {

@Autowired
 private MobileUserApplyAnchorService mobileuserapplyanchorservice;


@GetMapping
("/ApplyAnchor/{id}/MobileUser/getUser")
public MobileUser getUser(@PathVariable(name="id") String id58IK){
return mobileuserapplyanchorservice.getUser(id58IK);
}


@PutMapping
("/ApplyAnchor/{id}/MobileUser/setUser")
public void setUser(@PathVariable(name="id") String id58IK,@RequestBody MobileUser user){
mobileuserapplyanchorservice.setUser(id58IK,user);
}


}