package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Patient;
@RestController
@CrossOrigin
public class PatientOutdoorRegisterController {

@Autowired
 private PatientOutdoorRegisterService patientoutdoorregisterservice;


@GetMapping
("/OutdoorRegister/{id}/Patient/setPatient")
public OutdoorRegister setPatient(@PathVariable(name="id") Long id,@RequestParam Patient patient){
return patientoutdoorregisterservice.setPatient(id,patient);
}


@GetMapping
("/OutdoorRegister/{id}/Patient/getPatient")
public Patient getPatient(@PathVariable(name="id") Long id){
return patientoutdoorregisterservice.getPatient(id);
}


}