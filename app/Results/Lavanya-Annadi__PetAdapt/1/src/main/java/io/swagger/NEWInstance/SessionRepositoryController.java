package io.swagger.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SessionRepositoryController {

 private SessionRepository sessionrepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return sessionrepository.save(Object);
}


@GetMapping
("/findBySessionId")
public Session findBySessionId(@RequestParam(name = "session") String session){
  return sessionrepository.findBySessionId(session);
}


}