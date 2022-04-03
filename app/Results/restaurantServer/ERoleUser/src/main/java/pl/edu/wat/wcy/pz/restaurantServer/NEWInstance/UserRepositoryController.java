package pl.edu.wat.wcy.pz.restaurantServer.NEWInstance;
 import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.pz.restaurantServer.repository.UserRepository;
import java.util.*;
import pl.edu.wat.wcy.pz.restaurantServer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@CrossOrigin
public class UserRepositoryController {

@Autowired
 private UserRepository userrepository;


@GetMapping
("/findByMail")
public Optional<User> findByMail(@RequestParam(name = "mail") String mail){
  return userrepository.findByMail(mail);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return userrepository.findAll();
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return userrepository.findById((long)Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return userrepository.save((User)Object);
}

/*
@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return userrepository.deleteById((Long) Object);
}
*/

@GetMapping
("/existsByMail")
public Boolean existsByMail(@RequestParam(name = "mail") String mail){
  return userrepository.existsByMail(mail);
}


}