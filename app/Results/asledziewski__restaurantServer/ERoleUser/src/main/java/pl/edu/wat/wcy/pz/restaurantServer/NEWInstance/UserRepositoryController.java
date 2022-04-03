package pl.edu.wat.wcy.pz.restaurantServer.NEWInstance;
 import org.springframework.web.bind.annotation.*;
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
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return userrepository.findAll(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return userrepository.findById(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return userrepository.save(Object);
}


@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return userrepository.deleteById(Object);
}


@GetMapping
("/existsByMail")
public Boolean existsByMail(@RequestParam(name = "mail") String mail){
  return userrepository.existsByMail(mail);
}


}