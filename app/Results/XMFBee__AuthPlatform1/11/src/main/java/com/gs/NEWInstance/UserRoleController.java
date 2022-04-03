package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserRoleController {

 private UserRole userrole;

 private UserRole userrole;


@PutMapping
("/setUserId")
public void setUserId(@RequestParam(name = "userId") String userId){
userrole.setUserId(userId);
}


@PutMapping
("/setRoleId")
public void setRoleId(@RequestParam(name = "roleId") String roleId){
userrole.setRoleId(roleId);
}


}