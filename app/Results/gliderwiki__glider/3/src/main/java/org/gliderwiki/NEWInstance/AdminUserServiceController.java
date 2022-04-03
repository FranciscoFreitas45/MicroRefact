package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AdminUserServiceController {

 private AdminUserService adminuserservice;


@GetMapping
("/insertUser")
public int insertUser(@RequestParam(name = "map") Map<Integer,Map> map){
  return adminuserservice.insertUser(map);
}


}