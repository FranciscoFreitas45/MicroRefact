package hei2017.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TaskServiceController {

 private TaskService taskservice;


@GetMapping
("/findAll")
public List<Task> findAll(){
  return taskservice.findAll();
}


}