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


}