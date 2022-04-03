package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Register;
@RestController
@CrossOrigin
public class RegisterPictureInformationController {

@Autowired
 private RegisterPictureInformationService registerpictureinformationservice;


@PutMapping
("/PictureInformation/{id}/Register/setRegister")
public void setRegister(@PathVariable(name="id") Long id,@RequestBody Register register){
registerpictureinformationservice.setRegister(id,register);
}


@GetMapping
("/PictureInformation/{id}/Register/getRegister")
public Register getRegister(@PathVariable(name="id") Long id){
return registerpictureinformationservice.getRegister(id);
}


}