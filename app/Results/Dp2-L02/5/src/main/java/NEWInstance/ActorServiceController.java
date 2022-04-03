package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ActorServiceController {

 private ActorService actorservice;


@GetMapping
("/loggedAsActorBoolean")
public Boolean loggedAsActorBoolean(){
  return actorservice.loggedAsActorBoolean();
}


@GetMapping
("/loggedActor")
public Actor loggedActor(){
  return actorservice.loggedActor();
}


@GetMapping
("/getActorByUsername")
public Actor getActorByUsername(@RequestParam(name = "a") String a){
  return actorservice.getActorByUsername(a);
}


@PutMapping
("/loggedAsActor")
public void loggedAsActor(){
actorservice.loggedAsActor();
}


@GetMapping
("/save")
public Actor save(@RequestParam(name = "a") Actor a){
  return actorservice.save(a);
}


}