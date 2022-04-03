package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserLogDaoController {

 private UserLogDao userlogdao;


@GetMapping
("/findByUser")
public List<UserLog> findByUser(@RequestParam(name = "userid") long userid){
  return userlogdao.findByUser(userid);
}


}