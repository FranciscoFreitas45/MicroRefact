package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Vital;
@RestController
@CrossOrigin
public class VitalOutdoorRegisterController {

@Autowired
 private VitalOutdoorRegisterService vitaloutdoorregisterservice;


@GetMapping
("/OutdoorRegister/{id}/Vital/setVitals")
public OutdoorRegister setVitals(@PathVariable(name="id") Long id,@RequestParam Set<Vital> vitals){
return vitaloutdoorregisterservice.setVitals(id,vitals);
}


@GetMapping
("/OutdoorRegister/{id}/Vital/getVitals")
public Set<Vital> getVitals(@PathVariable(name="id") Long id){
return vitaloutdoorregisterservice.getVitals(id);
}


}