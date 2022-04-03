package hei2017.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserController {

 private UserDAO userdao;


@PutMapping
("/setUserTasks/{id}")
public void setUserTasks(@PathVariable(name = "id") Long id,@RequestParam(name = "userTasks") Set<Task> userTasks){
 userdao.setUserTasks(id,userTasks);
}


}