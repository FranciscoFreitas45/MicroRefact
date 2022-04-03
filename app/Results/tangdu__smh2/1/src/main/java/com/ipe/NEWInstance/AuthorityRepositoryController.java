package com.ipe.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AuthorityRepositoryController {

 private AuthorityRepository authorityrepository;


@GetMapping
("/getRoleByAuthority")
public Set<Resource> getRoleByAuthority(@RequestParam(name = "roleId") String roleId){
  return authorityrepository.getRoleByAuthority(roleId);
}


}