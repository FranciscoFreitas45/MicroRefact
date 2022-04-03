package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserServiceController {

 private UserService userservice;


@GetMapping
("/findmyemployuser")
public Page<User> findmyemployuser(@RequestParam(name = "page") int page,@RequestParam(name = "baseKey") String baseKey,@RequestParam(name = "parentid") long parentid){
  return userservice.findmyemployuser(page,baseKey,parentid);
}


}