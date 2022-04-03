package NEWInstance;
 import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class PersistentTokenRepositoryController {

 private PersistentTokenRepository persistenttokenrepository;


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return persistenttokenrepository.delete(Object);
}


@GetMapping
("/findByTokenDateBefore")
public List<PersistentToken> findByTokenDateBefore(@RequestParam(name = "localDate") LocalDate localDate){
  return persistenttokenrepository.findByTokenDateBefore(localDate);
}


@GetMapping
("/findByUser")
public List<PersistentToken> findByUser(@RequestParam(name = "user") User user){
  return persistenttokenrepository.findByUser(user);
}


}