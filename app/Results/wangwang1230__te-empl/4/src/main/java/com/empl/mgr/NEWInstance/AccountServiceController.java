package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AccountServiceController {

 private AccountService accountservice;


@GetMapping
("/login")
public JSONReturn login(@RequestParam(name = "name") String name,@RequestParam(name = "pass") String pass,@RequestParam(name = "request") HttpServletRequest request){
  return accountservice.login(name,pass,request);
}


@GetMapping
("/exit")
public JSONReturn exit(@RequestParam(name = "httpSession") HttpSession httpSession){
  return accountservice.exit(httpSession);
}


@GetMapping
("/mdoifyPass")
public JSONReturn mdoifyPass(@RequestParam(name = "password") String password,@RequestParam(name = "acctName") String acctName){
  return accountservice.mdoifyPass(password,acctName);
}


@GetMapping
("/findAccountInfo")
public JSONReturn findAccountInfo(@RequestParam(name = "acctName") String acctName){
  return accountservice.findAccountInfo(acctName);
}


@GetMapping
("/addAccount")
public JSONReturn addAccount(@RequestParam(name = "user") String user,@RequestParam(name = "nick") String nick,@RequestParam(name = "pass") String pass,@RequestParam(name = "acctName") String acctName){
  return accountservice.addAccount(user,nick,pass,acctName);
}


@GetMapping
("/findAccountList")
public JSONReturn findAccountList(@RequestParam(name = "page") int page,@RequestParam(name = "searchValue") String searchValue,@RequestParam(name = "acctName") String acctName){
  return accountservice.findAccountList(page,searchValue,acctName);
}


@GetMapping
("/findAccountPage")
public JSONReturn findAccountPage(@RequestParam(name = "page") int page,@RequestParam(name = "searchValue") String searchValue){
  return accountservice.findAccountPage(page,searchValue);
}


@GetMapping
("/delAccount")
public JSONReturn delAccount(@RequestParam(name = "id") long id,@RequestParam(name = "acctName") String acctName){
  return accountservice.delAccount(id,acctName);
}


@GetMapping
("/initPassword")
public JSONReturn initPassword(@RequestParam(name = "id") long id,@RequestParam(name = "acctName") String acctName){
  return accountservice.initPassword(id,acctName);
}


@GetMapping
("/modifyNickname")
public JSONReturn modifyNickname(@RequestParam(name = "id") long id,@RequestParam(name = "nickname") String nickname,@RequestParam(name = "acctName") String acctName){
  return accountservice.modifyNickname(id,nickname,acctName);
}


@GetMapping
("/findAccountById")
public JSONReturn findAccountById(@RequestParam(name = "id") long id){
  return accountservice.findAccountById(id);
}


@GetMapping
("/findRole")
public JSONReturn findRole(@RequestParam(name = "acctName") String acctName){
  return accountservice.findRole(acctName);
}


@GetMapping
("/addAccountRole")
public JSONReturn addAccountRole(@RequestParam(name = "id") long id,@RequestParam(name = "account") String account,@RequestParam(name = "add") boolean add,@RequestParam(name = "acctName") String acctName){
  return accountservice.addAccountRole(id,account,add,acctName);
}


@GetMapping
("/findAccountByName")
public TeAccount findAccountByName(@RequestParam(name = "userName") String userName){
  return accountservice.findAccountByName(userName);
}


}