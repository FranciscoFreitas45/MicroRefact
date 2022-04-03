package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import com.zis.shiro.service.SysService;
 import com.zis.shiro.bean.User;
@RestController
@CrossOrigin
public class SysServiceController {

 private SysService sysservice;


@GetMapping
("/findtUserById")
public User findtUserById(@RequestParam(name = "userId") Integer userId){
  return sysservice.findtUserById(userId);
}


}