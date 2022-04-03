package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LdapServiceController {

 private LdapService ldapservice;


@GetMapping
("/getUserLDAPGroups")
public List<String> getUserLDAPGroups(@RequestParam(name = "username") String username){
  return ldapservice.getUserLDAPGroups(username);
}


@GetMapping
("/isCasOss")
public boolean isCasOss(){
  return ldapservice.isCasOss();
}


}