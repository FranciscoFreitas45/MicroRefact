import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class UserRepositoryController {

 private UserRepository userrepository;


@GetMapping
("/findByMail")
public Optional<User> findByMail(@RequestParam(name = "mail") String mail){
  return userrepository.findByMail(mail);
}


@GetMapping
("/existsByMail")
public Boolean existsByMail(@RequestParam(name = "mail") String mail){
  return userrepository.existsByMail(mail);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return userrepository.save(Object);
}


}