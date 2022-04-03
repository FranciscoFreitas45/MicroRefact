package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Register;
@RestController
@CrossOrigin
public class RegisterInvestigationController {

@Autowired
 private RegisterInvestigationService registerinvestigationservice;


@GetMapping
("/Investigation/{id}/Register/getRegister")
public Register getRegister(@PathVariable(name="id") Long id){
return registerinvestigationservice.getRegister(id);
}


@PutMapping
("/Investigation/{id}/Register/setRegister")
public void setRegister(@PathVariable(name="id") Long id,@RequestBody Register register){
registerinvestigationservice.setRegister(id,register);
}


}