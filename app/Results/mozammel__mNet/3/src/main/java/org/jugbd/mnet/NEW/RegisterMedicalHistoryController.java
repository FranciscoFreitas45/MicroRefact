package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Register;
@RestController
@CrossOrigin
public class RegisterMedicalHistoryController {

@Autowired
 private RegisterMedicalHistoryService registermedicalhistoryservice;


@PutMapping
("/MedicalHistory/{id}/Register/setRegister")
public void setRegister(@PathVariable(name="id") Long id,@RequestBody Register register){
registermedicalhistoryservice.setRegister(id,register);
}


@GetMapping
("/MedicalHistory/{id}/Register/getRegister")
public Register getRegister(@PathVariable(name="id") Long id){
return registermedicalhistoryservice.getRegister(id);
}


}