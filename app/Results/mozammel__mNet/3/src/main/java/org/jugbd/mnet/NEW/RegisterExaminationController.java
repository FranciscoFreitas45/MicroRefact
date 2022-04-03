package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Register;
@RestController
@CrossOrigin
public class RegisterExaminationController {

@Autowired
 private RegisterExaminationService registerexaminationservice;


@GetMapping
("/Examination/{id}/Register/setRegister")
public Examination setRegister(@PathVariable(name="id") Long id,@RequestParam Register register){
return registerexaminationservice.setRegister(id,register);
}


@GetMapping
("/Examination/{id}/Register/getRegister")
public Register getRegister(@PathVariable(name="id") Long id){
return registerexaminationservice.getRegister(id);
}


}