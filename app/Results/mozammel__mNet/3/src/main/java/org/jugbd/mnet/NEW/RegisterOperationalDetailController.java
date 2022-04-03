package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Register;
@RestController
@CrossOrigin
public class RegisterOperationalDetailController {

@Autowired
 private RegisterOperationalDetailService registeroperationaldetailservice;


@PutMapping
("/OperationalDetail/{id}/Register/setRegister")
public void setRegister(@PathVariable(name="id") Long id,@RequestBody Register register){
registeroperationaldetailservice.setRegister(id,register);
}


@GetMapping
("/OperationalDetail/{id}/Register/getRegister")
public Register getRegister(@PathVariable(name="id") Long id){
return registeroperationaldetailservice.getRegister(id);
}


}