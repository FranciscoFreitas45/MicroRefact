package org.jugbd.mnet.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PatientDaoController {

 private PatientDao patientdao;


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return patientdao.findOne(Object);
}


}