package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.OutdoorRegister;
@RestController
@CrossOrigin
public class OutdoorRegisterVitalController {

@Autowired
 private OutdoorRegisterVitalService outdoorregistervitalservice;


@GetMapping
("/Vital/{id}/OutdoorRegister/setOutdoorRegister")
public Vital setOutdoorRegister(@PathVariable(name="id") Long id,@RequestParam OutdoorRegister outdoorRegister){
return outdoorregistervitalservice.setOutdoorRegister(id,outdoorRegister);
}


@GetMapping
("/Vital/{id}/OutdoorRegister/getOutdoorRegister")
public OutdoorRegister getOutdoorRegister(@PathVariable(name="id") Long id){
return outdoorregistervitalservice.getOutdoorRegister(id);
}


}