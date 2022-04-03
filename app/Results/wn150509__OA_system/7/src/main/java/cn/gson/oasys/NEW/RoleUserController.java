package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.role.Role;
@RestController
@CrossOrigin
public class RoleUserController {

@Autowired
 private RoleUserService roleuserservice;


@PutMapping
("/User/{id}/Role/setRole")
public void setRole(@PathVariable(name="id") Long roleId,@RequestBody Role role){
roleuserservice.setRole(roleId,role);
}


@GetMapping
("/User/{id}/Role/getRole")
public Role getRole(@PathVariable(name="id") Long roleId){
return roleuserservice.getRole(roleId);
}


}