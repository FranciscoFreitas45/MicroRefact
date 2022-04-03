package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Register;
@RestController
@CrossOrigin
public class RegisterChiefComplaintController {

@Autowired
 private RegisterChiefComplaintService registerchiefcomplaintservice;


@GetMapping
("/ChiefComplaint/{id}/Register/setRegister")
public ChiefComplaint setRegister(@PathVariable(name="id") Long id,@RequestParam Register register){
return registerchiefcomplaintservice.setRegister(id,register);
}


@GetMapping
("/ChiefComplaint/{id}/Register/getRegister")
public Register getRegister(@PathVariable(name="id") Long id){
return registerchiefcomplaintservice.getRegister(id);
}


}