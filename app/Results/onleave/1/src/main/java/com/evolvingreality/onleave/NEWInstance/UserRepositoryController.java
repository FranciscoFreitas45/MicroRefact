package com.evolvingreality.onleave.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import com.evolvingreality.onleave.repository.UserRepository;
 import java.util.*;
 import com.evolvingreality.onleave.model.User;
@RestController
@CrossOrigin
public class UserRepositoryController {

 private UserRepository userrepository;


@GetMapping
("/findAllByGroupMembers_SecurityGroupGroupNameIn")
public List<User> findAllByGroupMembers_SecurityGroupGroupNameIn(@RequestParam(name = "groupNames") Collection<String> groupNames){
  return userrepository.findAllByGroupMembers_SecurityGroupGroupNameIn(groupNames);
}


}