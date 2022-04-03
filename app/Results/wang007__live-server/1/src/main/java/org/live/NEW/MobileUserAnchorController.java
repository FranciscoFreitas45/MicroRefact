package org.live.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.live.entity.MobileUser;
@RestController
@CrossOrigin
public class MobileUserAnchorController {

@Autowired
 private MobileUserAnchorService mobileuseranchorservice;


@GetMapping
("/Anchor/{id}/MobileUser/getUser")
public MobileUser getUser(@PathVariable(name="id") String idQ2RW){
return mobileuseranchorservice.getUser(idQ2RW);
}


@PutMapping
("/Anchor/{id}/MobileUser/setUser")
public void setUser(@PathVariable(name="id") String idQ2RW,@RequestBody MobileUser user){
mobileuseranchorservice.setUser(idQ2RW,user);
}


}