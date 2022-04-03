package org.jugbd.mnet.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PatientServiceController {

 private PatientService patientservice;


@GetMapping
("/findOne")
public Patient findOne(@RequestParam(name = "id") Long id){
  return patientservice.findOne(id);
}


@GetMapping
("/count")
public long count(){
  return patientservice.count();
}


}