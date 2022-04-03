package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Vital;
@RestController
@CrossOrigin
public class VitalRegisterController {

@Autowired
 private VitalRegisterService vitalregisterservice;


@GetMapping
("/Register/{id}/Vital/getVitals")
public Set<Vital> getVitals(@PathVariable(name="id") Long id){
return vitalregisterservice.getVitals(id);
}


@PutMapping
("/Register/{id}/Vital/setVitals")
public void setVitals(@PathVariable(name="id") Long id,@RequestBody Set<Vital> vitals){
vitalregisterservice.setVitals(id,vitals);
}


}