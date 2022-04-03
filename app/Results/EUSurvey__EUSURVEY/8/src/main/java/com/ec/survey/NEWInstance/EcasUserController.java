package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EcasUserController {

 private EcasUser ecasuser;

 private EcasUser ecasuser;


@PutMapping
("/setInvited")
public void setInvited(@RequestParam(name = "invited") Date invited){
ecasuser.setInvited(invited);
}


@PutMapping
("/setReminded")
public void setReminded(@RequestParam(name = "reminded") Date reminded){
ecasuser.setReminded(reminded);
}


@PutMapping
("/setAnswers")
public void setAnswers(@RequestParam(name = "answers") int answers){
ecasuser.setAnswers(answers);
}


@PutMapping
("/setEmail")
public void setEmail(@RequestParam(name = "email") String email){
ecasuser.setEmail(email);
}


@PutMapping
("/setEcMoniker")
public void setEcMoniker(@RequestParam(name = "ecMoniker") String ecMoniker){
ecasuser.setEcMoniker(ecMoniker);
}


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
ecasuser.setName(name);
}


@PutMapping
("/setUserLDAPGroups")
public void setUserLDAPGroups(@RequestParam(name = "userLDAPGroups") Set<String> userLDAPGroups){
ecasuser.setUserLDAPGroups(userLDAPGroups);
}


}