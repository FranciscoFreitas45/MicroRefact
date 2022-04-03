package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.LifeStyle;
@RestController
@CrossOrigin
public class LifeStyleRegisterController {

@Autowired
 private LifeStyleRegisterService lifestyleregisterservice;


@PutMapping
("/Register/{id}/LifeStyle/setLifeStyle")
public void setLifeStyle(@PathVariable(name="id") Long id,@RequestBody LifeStyle lifeStyle){
lifestyleregisterservice.setLifeStyle(id,lifeStyle);
}


@GetMapping
("/Register/{id}/LifeStyle/getLifeStyle")
public LifeStyle getLifeStyle(@PathVariable(name="id") Long id){
return lifestyleregisterservice.getLifeStyle(id);
}


}