package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DiscussController {

 private DiscussDao discussdao;


@PutMapping
("/setVisitNum/{id}")
public void setVisitNum(@PathVariable(name = "id") Long id,@RequestParam(name = "visitNum") Integer visitNum){
 discussdao.setVisitNum(id,visitNum);
}


@PutMapping
("/setUsers/{id}")
public void setUsers(@PathVariable(name = "id") Long id,@RequestParam(name = "users") Set<User> users){
 discussdao.setUsers(id,users);
}


@PutMapping
("/setUser/{id}")
public void setUser(@PathVariable(name = "id") Long id,@RequestParam(name = "user") User user){
 discussdao.setUser(id,user);
}


@PutMapping
("/setModifyTime/{id}")
public void setModifyTime(@PathVariable(name = "id") Long id,@RequestParam(name = "modifyTime") Date modifyTime){
 discussdao.setModifyTime(id,modifyTime);
}


@PutMapping
("/setTitle/{id}")
public void setTitle(@PathVariable(name = "id") Long id,@RequestParam(name = "title") String title){
 discussdao.setTitle(id,title);
}


@PutMapping
("/setContent/{id}")
public void setContent(@PathVariable(name = "id") Long id,@RequestParam(name = "content") String content){
 discussdao.setContent(id,content);
}


}