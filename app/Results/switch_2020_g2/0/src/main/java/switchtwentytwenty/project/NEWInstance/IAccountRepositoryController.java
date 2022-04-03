package switchtwentytwenty.project.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IAccountRepositoryController {

 private IAccountRepository iaccountrepository;


@GetMapping
("/findByID")
public Account findByID(@RequestParam(name = "id") AccountID id){
  return iaccountrepository.findByID(id);
}


@PutMapping
("/save")
public void save(@RequestParam(name = "entity") Account entity){
iaccountrepository.save(entity);
}


}