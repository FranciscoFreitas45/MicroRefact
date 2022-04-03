package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RoleController {

 private Role role;


@PutMapping
("/setRoleStatus")
public void setRoleStatus(@RequestParam(name = "roleStatus") String roleStatus){
role.setRoleStatus(roleStatus);
}


}