package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LogServiceController {

 private LogService logservice;


@GetMapping
("/getEntity")
public Object getEntity(@RequestParam(name = "Object") Object Object){
  return logservice.getEntity(Object);
}


}