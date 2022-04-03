package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.OutdoorRegister;
@RestController
@CrossOrigin
public class OutdoorRegisterExaminationController {

@Autowired
 private OutdoorRegisterExaminationService outdoorregisterexaminationservice;


@GetMapping
("/Examination/{id}/OutdoorRegister/setOutdoorRegister")
public Examination setOutdoorRegister(@PathVariable(name="id") Long id,@RequestParam OutdoorRegister outdoorRegister){
return outdoorregisterexaminationservice.setOutdoorRegister(id,outdoorRegister);
}


@GetMapping
("/Examination/{id}/OutdoorRegister/getOutdoorRegister")
public OutdoorRegister getOutdoorRegister(@PathVariable(name="id") Long id){
return outdoorregisterexaminationservice.getOutdoorRegister(id);
}


}