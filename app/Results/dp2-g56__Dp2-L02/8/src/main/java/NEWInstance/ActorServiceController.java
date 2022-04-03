package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ActorServiceController {

 private ActorService actorservice;


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


@GetMapping
("/findAll")
public List<Actor> findAll(){
  return actorservice.findAll();
}


@GetMapping
("/save")
public Actor save(@RequestParam(name = "a") Actor a){
  return actorservice.save(a);
}


@PutMapping
("/loggedAsActor")
public void loggedAsActor(){
actorservice.loggedAsActor();
}


}