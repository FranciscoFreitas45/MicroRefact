package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.ChiefComplaint;
@RestController
@CrossOrigin
public class ChiefComplaintOutdoorRegisterController {

@Autowired
 private ChiefComplaintOutdoorRegisterService chiefcomplaintoutdoorregisterservice;


@GetMapping
("/OutdoorRegister/{id}/ChiefComplaint/setChiefComplaint")
public OutdoorRegister setChiefComplaint(@PathVariable(name="id") Long id,@RequestParam ChiefComplaint chiefComplaint){
return chiefcomplaintoutdoorregisterservice.setChiefComplaint(id,chiefComplaint);
}


@GetMapping
("/OutdoorRegister/{id}/ChiefComplaint/getChiefComplaint")
public ChiefComplaint getChiefComplaint(@PathVariable(name="id") Long id){
return chiefcomplaintoutdoorregisterservice.getChiefComplaint(id);
}


}