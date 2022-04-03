package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AgentUserContactsRepositoryController {

 private AgentUserContactsRepository agentusercontactsrepository;


@GetMapping
("/findByUseridAndOrgi")
public List<AgentUserContacts> findByUseridAndOrgi(@RequestParam(name = "userid") String userid,@RequestParam(name = "orgi") String orgi){
  return agentusercontactsrepository.findByUseridAndOrgi(userid,orgi);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return agentusercontactsrepository.save(Object);
}


}