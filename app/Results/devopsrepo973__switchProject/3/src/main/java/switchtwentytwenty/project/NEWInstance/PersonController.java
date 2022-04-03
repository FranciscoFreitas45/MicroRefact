package switchtwentytwenty.project.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PersonController {

 private Person person;


@GetMapping
("/addAccountID")
public boolean addAccountID(@RequestParam(name = "newAccountID") AccountID newAccountID){
  return person.addAccountID(newAccountID);
}


}