package com.softserve.edu.Resources.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class VerificationTokenDAOController {

 private VerificationTokenDAO verificationtokendao;


@GetMapping
("/makePersistent")
public VerificationToken makePersistent(@RequestParam(name = "verificationToken") VerificationToken verificationToken){
  return verificationtokendao.makePersistent(verificationToken);
}


@GetMapping
("/findByToken")
public VerificationToken findByToken(@RequestParam(name = "token") String token){
  return verificationtokendao.findByToken(token);
}


@PutMapping
("/makeTransient")
public void makeTransient(@RequestParam(name = "verificationToken") VerificationToken verificationToken){
verificationtokendao.makeTransient(verificationToken);
}


}