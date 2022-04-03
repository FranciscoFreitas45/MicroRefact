package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LdapDBServiceController {

 private LdapDBService ldapdbservice;


@GetMapping
("/getDepartments")
public String[] getDepartments(@RequestParam(name = "domain") String domain,@RequestParam(name = "term") String term,@RequestParam(name = "prefix") boolean prefix,@RequestParam(name = "removeTerm") boolean removeTerm){
  return ldapdbservice.getDepartments(domain,term,prefix,removeTerm);
}


}