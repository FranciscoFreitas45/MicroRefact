package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Patient;
@RestController
@CrossOrigin
public class PatientRegisterController {

@Autowired
 private PatientRegisterService patientregisterservice;


@PutMapping
("/Register/{id}/Patient/setPatient")
public void setPatient(@PathVariable(name="id") Long id,@RequestBody Patient patient){
patientregisterservice.setPatient(id,patient);
}


@GetMapping
("/Register/{id}/Patient/getPatient")
public Patient getPatient(@PathVariable(name="id") Long id){
return patientregisterservice.getPatient(id);
}


}