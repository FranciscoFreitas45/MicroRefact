package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserController {

 private User user;


@PutMapping
("/setDisabledesk/{id}")
public void setDisabledesk(@PathVariable(name = "id") String id,@RequestParam(name = "disabledesk") boolean disabledesk){
 userrepository.setDisabledesk(id,disabledesk);
}


@PutMapping
("/setEmail/{id}")
public void setEmail(@PathVariable(name = "id") String id,@RequestParam(name = "email") String email){
 userrepository.setEmail(id,email);
}


@GetMapping
("/isAgent/{id}")
public boolean isAgent(@PathVariable(name = "id") String id){
 return userrepository.isAgent(id);
}


@PutMapping
("/setPassword/{id}")
public void setPassword(@PathVariable(name = "id") String id,@RequestParam(name = "password") String password){
 userrepository.setPassword(id,password);
}


@PutMapping
("/setCreatetime/{id}")
public void setCreatetime(@PathVariable(name = "id") String id,@RequestParam(name = "createtime") Date createtime){
 userrepository.setCreatetime(id,createtime);
}


@PutMapping
("/setUpdatetime/{id}")
public void setUpdatetime(@PathVariable(name = "id") String id,@RequestParam(name = "updatetime") Date updatetime){
 userrepository.setUpdatetime(id,updatetime);
}


@PutMapping
("/setRoleList/{id}")
public void setRoleList(@PathVariable(name = "id") String id,@RequestParam(name = "roleList") List<Role> roleList){
 userrepository.setRoleList(id,roleList);
}


@PutMapping
("/setRoleAuthMap/{id}")
public void setRoleAuthMap(@PathVariable(name = "id") String id,@RequestParam(name = "roleAuthMap") Map<String,Object> roleAuthMap){
 userrepository.setRoleAuthMap(id,roleAuthMap);
}


@PutMapping
("/setUsername/{id}")
public void setUsername(@PathVariable(name = "id") String id,@RequestParam(name = "username") String username){
 userrepository.setUsername(id,username);
}


@GetMapping
("/isSuperuser/{id}")
public boolean isSuperuser(@PathVariable(name = "id") String id){
 return userrepository.isSuperuser(id);
}


@PutMapping
("/setSessionid/{id}")
public void setSessionid(@PathVariable(name = "id") String id,@RequestParam(name = "sessionid") String sessionid){
 userrepository.setSessionid(id,sessionid);
}


@PutMapping
("/setId/{id}")
public void setId(@PathVariable(name = "id") String id,@RequestParam(name = "id") String id){
 userrepository.setId(id,id);
}


@PutMapping
("/setOrgi/{id}")
public void setOrgi(@PathVariable(name = "id") String id,@RequestParam(name = "orgi") String orgi){
 userrepository.setOrgi(id,orgi);
}


}