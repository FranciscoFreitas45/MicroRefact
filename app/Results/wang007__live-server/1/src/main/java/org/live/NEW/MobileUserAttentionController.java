package org.live.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.live.entity.MobileUser;
@RestController
@CrossOrigin
public class MobileUserAttentionController {

@Autowired
 private MobileUserAttentionService mobileuserattentionservice;


@GetMapping
("/Attention/{id}/MobileUser/getUser")
public MobileUser getUser(@PathVariable(name="id") String id1YA2){
return mobileuserattentionservice.getUser(id1YA2);
}


@PutMapping
("/Attention/{id}/MobileUser/setUser")
public void setUser(@PathVariable(name="id") String id1YA2,@RequestBody MobileUser user){
mobileuserattentionservice.setUser(id1YA2,user);
}


}