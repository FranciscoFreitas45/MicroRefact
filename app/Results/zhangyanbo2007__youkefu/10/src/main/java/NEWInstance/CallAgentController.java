package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CallAgentController {

 private CallAgent callagent;


@GetMapping
("/getDisnum")
public int getDisnum(){
  return callagent.getDisnum();
}


@GetMapping
("/getDisnames")
public AtomicInteger getDisnames(){
  return callagent.getDisnames();
}


@GetMapping
("/incrementAndGet")
public Object incrementAndGet(@RequestParam(name = "Object") Object Object){
  return callagent.incrementAndGet(Object);
}


@GetMapping
("/getDistype")
public String getDistype(){
  return callagent.getDistype();
}


@GetMapping
("/getDistarget")
public String getDistarget(){
  return callagent.getDistarget();
}


@GetMapping
("/getOrgan")
public String getOrgan(){
  return callagent.getOrgan();
}


@GetMapping
("/intValue")
public Object intValue(@RequestParam(name = "Object") Object Object){
  return callagent.intValue(Object);
}


}