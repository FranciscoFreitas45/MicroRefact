package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AccountDaoController {

 private AccountDao accountdao;


@GetMapping
("/findUniqueByProperty")
public Object findUniqueByProperty(@RequestParam(name = "Object") Object Object){
  return accountdao.findUniqueByProperty(Object);
}


}