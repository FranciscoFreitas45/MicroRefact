package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AccountDaoController {

 private AccountDao accountdao;


@GetMapping
("/findByUsernameOrEmail")
public Account findByUsernameOrEmail(@RequestParam(name = "username") String username){
  return accountdao.findByUsernameOrEmail(username);
}


@PutMapping
("/decreaseRiverQuota")
public void decreaseRiverQuota(@RequestParam(name = "account") Account account,@RequestParam(name = "decrement") int decrement){
accountdao.decreaseRiverQuota(account,decrement);
}


@PutMapping
("/increaseRiverQuota")
public void increaseRiverQuota(@RequestParam(name = "account") Account account,@RequestParam(name = "increment") int increment){
accountdao.increaseRiverQuota(account,increment);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return accountdao.findById(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return accountdao.update(Object);
}


}