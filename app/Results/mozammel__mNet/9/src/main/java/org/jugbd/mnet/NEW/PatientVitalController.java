package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Patient;
@RestController
@CrossOrigin
public class PatientVitalController {

@Autowired
 private PatientVitalService patientvitalservice;


@GetMapping
("/Vital/{id}/Patient/setPatient")
public Vital setPatient(@PathVariable(name="id") Long id,@RequestParam Patient patient){
return patientvitalservice.setPatient(id,patient);
}


@GetMapping
("/Vital/{id}/Patient/getPatient")
public Patient getPatient(@PathVariable(name="id") Long id){
return patientvitalservice.getPatient(id);
}


}