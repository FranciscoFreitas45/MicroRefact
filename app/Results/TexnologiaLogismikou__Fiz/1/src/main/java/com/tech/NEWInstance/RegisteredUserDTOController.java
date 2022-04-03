package com.tech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RegisteredUserDTOController {

 private RegisteredUserDTO registereduserdto;

 private RegisteredUserDTO registereduserdto;


@GetMapping
("/validate")
public Pair<Boolean,ResponseEntity> validate(){
  return registereduserdto.validate();
}


}