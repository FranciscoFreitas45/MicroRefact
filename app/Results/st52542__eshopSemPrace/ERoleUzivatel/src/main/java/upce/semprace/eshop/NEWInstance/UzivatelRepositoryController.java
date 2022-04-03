package upce.semprace.eshop.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UzivatelRepositoryController {

 private UzivatelRepository uzivatelrepository;


@GetMapping
("/existsByUsername")
public Boolean existsByUsername(@RequestParam(name = "username") String username){
  return uzivatelrepository.existsByUsername(username);
}


@GetMapping
("/existsByEmail")
public Boolean existsByEmail(@RequestParam(name = "email") String email){
  return uzivatelrepository.existsByEmail(email);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return uzivatelrepository.save(Object);
}


@GetMapping
("/findByUsername")
public Optional<Uzivatel> findByUsername(@RequestParam(name = "username") String username){
  return uzivatelrepository.findByUsername(username);
}


@GetMapping
("/get")
public Object get(@RequestParam(name = "Object") Object Object){
  return uzivatelrepository.get(Object);
}


@GetMapping
("/getId")
public Object getId(@RequestParam(name = "Object") Object Object){
  return uzivatelrepository.getId(Object);
}


@GetMapping
("/findById")
public Optional<Uzivatel> findById(@RequestParam(name = "id") Long id){
  return uzivatelrepository.findById(id);
}


}