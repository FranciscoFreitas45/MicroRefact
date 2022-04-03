package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LogRepositoryController {

 private LogRepository logrepository;


@GetMapping
("/findByOrgi")
public Page<Log> findByOrgi(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "page") Pageable page){
  return logrepository.findByOrgi(orgi,page);
}


@GetMapping
("/findByOrgiAndLevels")
public Page<Log> findByOrgiAndLevels(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "levels") String levels,@RequestParam(name = "page") Pageable page){
  return logrepository.findByOrgiAndLevels(orgi,levels,page);
}


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return logrepository.findOne(Object);
}


}