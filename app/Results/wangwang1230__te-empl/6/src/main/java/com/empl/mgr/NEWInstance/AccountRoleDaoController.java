package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AccountRoleDaoController {

 private AccountRoleDao accountroledao;


@GetMapping
("/findByAcctNameAndRoleLabel")
public List<TeAccountRole> findByAcctNameAndRoleLabel(@RequestParam(name = "account") String account,@RequestParam(name = "roleLabel") String roleLabel){
  return accountroledao.findByAcctNameAndRoleLabel(account,roleLabel);
}


@PutMapping
("/delByAcctNameAndRoleLabel")
public void delByAcctNameAndRoleLabel(@RequestParam(name = "account") String account,@RequestParam(name = "roleLabel") String roleLabel){
accountroledao.delByAcctNameAndRoleLabel(account,roleLabel);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return accountroledao.save(Object);
}


}