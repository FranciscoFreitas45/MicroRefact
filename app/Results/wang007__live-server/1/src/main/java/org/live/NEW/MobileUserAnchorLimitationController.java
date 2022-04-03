package org.live.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.live.entity.MobileUser;
@RestController
@CrossOrigin
public class MobileUserAnchorLimitationController {

@Autowired
 private MobileUserAnchorLimitationService mobileuseranchorlimitationservice;


@GetMapping
("/AnchorLimitation/{id}/MobileUser/getUser")
public MobileUser getUser(@PathVariable(name="id") String idL5CE){
return mobileuseranchorlimitationservice.getUser(idL5CE);
}


@PutMapping
("/AnchorLimitation/{id}/MobileUser/setUser")
public void setUser(@PathVariable(name="id") String idL5CE,@RequestBody MobileUser user){
mobileuseranchorlimitationservice.setUser(idL5CE,user);
}


}