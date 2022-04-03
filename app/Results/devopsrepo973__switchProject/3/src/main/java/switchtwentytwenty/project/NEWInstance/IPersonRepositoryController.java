package switchtwentytwenty.project.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IPersonRepositoryController {

 private IPersonRepository ipersonrepository;


@GetMapping
("/findByID")
public Person findByID(@RequestParam(name = "id") Email id){
  return ipersonrepository.findByID(id);
}


@PutMapping
("/save")
public void save(@RequestParam(name = "entity") Person entity){
ipersonrepository.save(entity);
}


@GetMapping
("/findByFamilyID")
public PersonList findByFamilyID(@RequestParam(name = "familyID") FamilyID familyID){
  return ipersonrepository.findByFamilyID(familyID);
}


@GetMapping
("/existsByID")
public boolean existsByID(@RequestParam(name = "id") Email id){
  return ipersonrepository.existsByID(id);
}


@GetMapping
("/existsByFamilyIDAndVat")
public boolean existsByFamilyIDAndVat(@RequestParam(name = "familyID") FamilyID familyID,@RequestParam(name = "vat") VAT vat){
  return ipersonrepository.existsByFamilyIDAndVat(familyID,vat);
}


@GetMapping
("/findByAccountID")
public Person findByAccountID(@RequestParam(name = "accountID") AccountID accountID){
  return ipersonrepository.findByAccountID(accountID);
}


}