package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StatusEventRepositoryController {

 private StatusEventRepository statuseventrepository;


@GetMapping
("/findById")
public StatusEvent findById(@RequestParam(name = "id") String id){
  return statuseventrepository.findById(id);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return statuseventrepository.save(Object);
}


}