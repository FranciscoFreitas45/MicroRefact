package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Register;
@RestController
@CrossOrigin
public class RegisterVitalController {

@Autowired
 private RegisterVitalService registervitalservice;


@GetMapping
("/Vital/{id}/Register/setRegister")
public Vital setRegister(@PathVariable(name="id") Long id,@RequestParam Register register){
return registervitalservice.setRegister(id,register);
}


@GetMapping
("/Vital/{id}/Register/getRegister")
public Register getRegister(@PathVariable(name="id") Long id){
return registervitalservice.getRegister(id);
}


}