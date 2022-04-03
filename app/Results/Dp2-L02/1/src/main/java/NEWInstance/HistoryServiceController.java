package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class HistoryServiceController {

 private HistoryService historyservice;


@GetMapping
("/findAll")
public List<History> findAll(){
  return historyservice.findAll();
}


@GetMapping
("/isEmpty")
public Object isEmpty(@RequestParam(name = "Object") Object Object){
  return historyservice.isEmpty(Object);
}


}