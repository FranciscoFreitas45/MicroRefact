package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MenuSysServiceController {

 private MenuSysService menusysservice;


@PutMapping
("/findMenuSys")
public void findMenuSys(@RequestParam(name = "req") HttpServletRequest req,@RequestParam(name = "user") User user){
menusysservice.findMenuSys(req,user);
}


}