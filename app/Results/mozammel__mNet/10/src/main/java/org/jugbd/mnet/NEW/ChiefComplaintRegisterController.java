package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.ChiefComplaint;
@RestController
@CrossOrigin
public class ChiefComplaintRegisterController {

@Autowired
 private ChiefComplaintRegisterService chiefcomplaintregisterservice;


@GetMapping
("/Register/{id}/ChiefComplaint/getChiefComplaint")
public ChiefComplaint getChiefComplaint(@PathVariable(name="id") Long id){
return chiefcomplaintregisterservice.getChiefComplaint(id);
}


@PutMapping
("/Register/{id}/ChiefComplaint/setChiefComplaint")
public void setChiefComplaint(@PathVariable(name="id") Long id,@RequestBody ChiefComplaint chiefComplaint){
chiefcomplaintregisterservice.setChiefComplaint(id,chiefComplaint);
}


}