package com.sda.inTeams.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserRepositoryController {

 private UserRepository userrepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return userrepository.findById(Object);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return userrepository.findAll(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return userrepository.delete(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return userrepository.save(Object);
}


@GetMapping
("/findAllByTeamsContaining")
public List<User> findAllByTeamsContaining(@RequestParam(name = "team") Team team){
  return userrepository.findAllByTeamsContaining(team);
}


@GetMapping
("/findByUniqueInvitationId")
public Optional<User> findByUniqueInvitationId(@RequestParam(name = "uniqueInvitationId") String uniqueInvitationId){
  return userrepository.findByUniqueInvitationId(uniqueInvitationId);
}


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return userrepository.count(Object);
}


@GetMapping
("/saveAll")
public Object saveAll(@RequestParam(name = "Object") Object Object){
  return userrepository.saveAll(Object);
}


@GetMapping
("/findByUsername")
public Optional<User> findByUsername(@RequestParam(name = "username") String username){
  return userrepository.findByUsername(username);
}


}