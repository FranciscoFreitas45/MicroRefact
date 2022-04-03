package sn.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AccountServiceController {

 private AccountService accountservice;


@GetMapping
("/findCurrentUser")
public Person findCurrentUser(){
  return accountservice.findCurrentUser();
}


@GetMapping
("/getPersonResponse")
public PersonResponse getPersonResponse(@RequestParam(name = "person") Person person){
  return accountservice.getPersonResponse(person);
}


@GetMapping
("/exists")
public boolean exists(@RequestParam(name = "personId") long personId){
  return accountservice.exists(personId);
}


}