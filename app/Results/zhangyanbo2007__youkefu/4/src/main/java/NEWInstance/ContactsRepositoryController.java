package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ContactsRepositoryController {

 private ContactsRepository contactsrepository;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return contactsrepository.findAll(Object);
}


@GetMapping
("/findByOrgi")
public Object findByOrgi(@RequestParam(name = "Object") Object Object){
  return contactsrepository.findByOrgi(Object);
}


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return contactsrepository.findOne(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return contactsrepository.save(Object);
}


}