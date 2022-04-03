package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.OutdoorRegister;
@RestController
@CrossOrigin
public class OutdoorRegisterChiefComplaintController {

@Autowired
 private OutdoorRegisterChiefComplaintService outdoorregisterchiefcomplaintservice;


@GetMapping
("/ChiefComplaint/{id}/OutdoorRegister/setOutdoorRegister")
public ChiefComplaint setOutdoorRegister(@PathVariable(name="id") Long id,@RequestParam OutdoorRegister outdoorRegister){
return outdoorregisterchiefcomplaintservice.setOutdoorRegister(id,outdoorRegister);
}


@GetMapping
("/ChiefComplaint/{id}/OutdoorRegister/getOutdoorRegister")
public OutdoorRegister getOutdoorRegister(@PathVariable(name="id") Long id){
return outdoorregisterchiefcomplaintservice.getOutdoorRegister(id);
}


}