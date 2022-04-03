package ink.champ.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserRepositoryController {

 private UserRepository userrepository;


@GetMapping
("/findUserByNameContainingIgnoreCaseOrUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase")
public List<User> findUserByNameContainingIgnoreCaseOrUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(@RequestParam(name = "search1") String search1,@RequestParam(name = "search2") String search2,@RequestParam(name = "search3") String search3,@RequestParam(name = "sort") Sort sort){
  return userrepository.findUserByNameContainingIgnoreCaseOrUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(search1,search2,search3,sort);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return userrepository.findById(Object);
}


@GetMapping
("/findByUsername")
public User findByUsername(@RequestParam(name = "username") String username){
  return userrepository.findByUsername(username);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return userrepository.save(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return userrepository.delete(Object);
}


}