package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SessionTypeRepositoryController {

 private SessionTypeRepository sessiontyperepository;


@GetMapping
("/findByOrgiAndCtype")
public List<SessionType> findByOrgiAndCtype(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "ctype") String ctype){
  return sessiontyperepository.findByOrgiAndCtype(orgi,ctype);
}


@GetMapping
("/findById")
public SessionType findById(@RequestParam(name = "id") String id){
  return sessiontyperepository.findById(id);
}


}