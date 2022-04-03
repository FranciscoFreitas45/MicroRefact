package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TaskDaoController {

 private TaskDao taskdao;


@GetMapping
("/countfinish")
public Integer countfinish(@RequestParam(name = "status") Long status,@RequestParam(name = "userid") Long userid){
  return taskdao.countfinish(status,userid);
}


}