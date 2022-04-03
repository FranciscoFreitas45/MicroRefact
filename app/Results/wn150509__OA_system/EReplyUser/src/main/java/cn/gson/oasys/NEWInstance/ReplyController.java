package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ReplyController {

 private ReplyDao replydao;


@PutMapping
("/setUsers/{id}")
public void setUsers(@PathVariable(name = "id") Long id,@RequestParam(name = "users") Set<User> users){
 replydao.setUsers(id,users);
}


}