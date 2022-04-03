package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TaskuserDaoController {

 private TaskuserDao taskuserdao;


@GetMapping
("/findByUserIdAndStatusId")
public List<Taskuser> findByUserIdAndStatusId(@RequestParam(name = "user") User user,@RequestParam(name = "id") Integer id){
  return taskuserdao.findByUserIdAndStatusId(user,id);
}


}