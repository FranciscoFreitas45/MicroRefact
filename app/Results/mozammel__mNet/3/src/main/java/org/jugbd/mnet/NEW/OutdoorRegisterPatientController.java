package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.OutdoorRegister;
@RestController
@CrossOrigin
public class OutdoorRegisterPatientController {

@Autowired
 private OutdoorRegisterPatientService outdoorregisterpatientservice;


@GetMapping
("/Patient/{id}/OutdoorRegister/getOutdoorRegisters")
public Set<OutdoorRegister> getOutdoorRegisters(@PathVariable(name="id") Long id){
return outdoorregisterpatientservice.getOutdoorRegisters(id);
}


@GetMapping
("/Patient/{id}/OutdoorRegister/setOutdoorRegisters")
public Patient setOutdoorRegisters(@PathVariable(name="id") Long id,@RequestParam Set<OutdoorRegister> outdoorRegisters){
return outdoorregisterpatientservice.setOutdoorRegisters(id,outdoorRegisters);
}


}