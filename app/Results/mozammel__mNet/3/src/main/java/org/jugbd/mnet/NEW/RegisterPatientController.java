package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Register;
@RestController
@CrossOrigin
public class RegisterPatientController {

@Autowired
 private RegisterPatientService registerpatientservice;


@PutMapping
("/Patient/{id}/Register/setRegisters")
public void setRegisters(@PathVariable(name="id") Long id,@RequestBody Set<Register> registers){
registerpatientservice.setRegisters(id,registers);
}


@GetMapping
("/Patient/{id}/Register/getRegisters")
public Set<Register> getRegisters(@PathVariable(name="id") Long id){
return registerpatientservice.getRegisters(id);
}


}