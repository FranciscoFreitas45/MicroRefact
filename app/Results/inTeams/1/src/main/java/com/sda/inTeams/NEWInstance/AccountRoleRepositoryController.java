package com.sda.inTeams.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import com.sda.inTeams.DTO.*;
import java.util.*;
import com.sda.inTeams.repository.*;
import com.sda.inTeams.model.User.*;
@RestController
@CrossOrigin
public class AccountRoleRepositoryController {

 private AccountRoleRepository accountrolerepository;


@GetMapping
("/findByName")
public Optional<AccountRole> findByName(@RequestParam(name = "name") String name){
  return accountrolerepository.findByName(name);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return accountrolerepository.save((AccountRole) Object);
}


}