package cn.maxcj.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ShiroUserController {

 private ShiroUser shirouser;

 private ShiroUser shirouser;


@PutMapping
("/setId")
public void setId(@RequestParam(name = "id") Integer id){
shirouser.setId(id);
}


@PutMapping
("/setAccount")
public void setAccount(@RequestParam(name = "account") String account){
shirouser.setAccount(account);
}


@PutMapping
("/setDeptId")
public void setDeptId(@RequestParam(name = "deptId") Integer deptId){
shirouser.setDeptId(deptId);
}


@PutMapping
("/setDeptName")
public void setDeptName(@RequestParam(name = "deptName") String deptName){
shirouser.setDeptName(deptName);
}


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
shirouser.setName(name);
}


@PutMapping
("/setRoleList")
public void setRoleList(@RequestParam(name = "roleList") List<Integer> roleList){
shirouser.setRoleList(roleList);
}


@PutMapping
("/setRoleNames")
public void setRoleNames(@RequestParam(name = "roleNames") List<String> roleNames){
shirouser.setRoleNames(roleNames);
}


}