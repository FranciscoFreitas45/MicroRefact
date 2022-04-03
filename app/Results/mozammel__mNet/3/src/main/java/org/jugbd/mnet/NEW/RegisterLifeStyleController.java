package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Register;
@RestController
@CrossOrigin
public class RegisterLifeStyleController {

@Autowired
 private RegisterLifeStyleService registerlifestyleservice;


@PutMapping
("/LifeStyle/{id}/Register/setRegister")
public void setRegister(@PathVariable(name="id") Long id,@RequestBody Register register){
registerlifestyleservice.setRegister(id,register);
}


@GetMapping
("/LifeStyle/{id}/Register/getRegister")
public Register getRegister(@PathVariable(name="id") Long id){
return registerlifestyleservice.getRegister(id);
}


}