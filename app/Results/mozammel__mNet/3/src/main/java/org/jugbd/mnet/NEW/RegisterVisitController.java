package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Register;
@RestController
@CrossOrigin
public class RegisterVisitController {

@Autowired
 private RegisterVisitService registervisitservice;


@GetMapping
("/Visit/{id}/Register/setRegister")
public Visit setRegister(@PathVariable(name="id") Long id,@RequestParam Register register){
return registervisitservice.setRegister(id,register);
}


@GetMapping
("/Visit/{id}/Register/getRegister")
public Register getRegister(@PathVariable(name="id") Long id){
return registervisitservice.getRegister(id);
}


}