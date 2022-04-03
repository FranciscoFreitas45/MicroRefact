package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AccountServiceController {

 private AccountService accountservice;


@GetMapping
("/list")
public Object list(@RequestParam(name = "Object") Object Object){
  return accountservice.list(Object);
}


@GetMapping
("/getEntityBy")
public Object getEntityBy(@RequestParam(name = "Object") Object Object){
  return accountservice.getEntityBy(Object);
}


@GetMapping
("/findMenusBy")
public List<SysOrgMenu> findMenusBy(@RequestParam(name = "orgId") String orgId){
  return accountservice.findMenusBy(orgId);
}


@GetMapping
("/findAllMenus")
public List<Menu> findAllMenus(){
  return accountservice.findAllMenus();
}


@GetMapping
("/unique")
public Object unique(@RequestParam(name = "Object") Object Object){
  return accountservice.unique(Object);
}


@GetMapping
("/findUserByLoginName")
public User findUserByLoginName(@RequestParam(name = "loginName") String loginName){
  return accountservice.findUserByLoginName(loginName);
}


}