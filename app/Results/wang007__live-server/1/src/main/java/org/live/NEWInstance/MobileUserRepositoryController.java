package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MobileUserRepositoryController {

 private MobileUserRepository mobileuserrepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return mobileuserrepository.save(Object);
}


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return mobileuserrepository.findOne(Object);
}


@GetMapping
("/findMobileUserByAccount")
public MobileUser findMobileUserByAccount(@RequestParam(name = "account") String account){
  return mobileuserrepository.findMobileUserByAccount(account);
}


}