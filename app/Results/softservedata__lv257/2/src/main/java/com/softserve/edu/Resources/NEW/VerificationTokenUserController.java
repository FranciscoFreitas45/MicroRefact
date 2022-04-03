package com.softserve.edu.Resources.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.entity.VerificationToken;
@RestController
@CrossOrigin
public class VerificationTokenUserController {

@Autowired
 private VerificationTokenUserService verificationtokenuserservice;


@GetMapping
("/User/{id}/VerificationToken/getVerificationToken")
public VerificationToken getVerificationToken(@PathVariable(name="id") Long id69IL){
return verificationtokenuserservice.getVerificationToken(id69IL);
}


@GetMapping
("/User/{id}/VerificationToken/setVerificationToken")
public User setVerificationToken(@PathVariable(name="id") Long id69IL,@RequestParam VerificationToken verificationToken){
return verificationtokenuserservice.setVerificationToken(id69IL,verificationToken);
}


}