package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserController {

 private UserRepository userrepository;


@PutMapping
("/setLastLoginTime/{id}")
public void setLastLoginTime(@PathVariable(name = "id") String id,@RequestParam(name = "lastLoginTime") Date lastLoginTime){
 userrepository.setLastLoginTime(id,lastLoginTime);
}


@PutMapping
("/setLastLoginIp/{id}")
public void setLastLoginIp(@PathVariable(name = "id") String id,@RequestParam(name = "lastLoginIp") String lastLoginIp){
 userrepository.setLastLoginIp(id,lastLoginIp);
}


}