package switchtwentytwenty.project.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FamilyController {

 private Family family;

 private Family family;


@GetMapping
("/isAdministrator")
public boolean isAdministrator(@RequestParam(name = "personID") Email personID){
  return family.isAdministrator(personID);
}


@GetMapping
("/ownsCashAccount")
public boolean ownsCashAccount(@RequestParam(name = "accountID") AccountID accountID){
  return family.ownsCashAccount(accountID);
}


@GetMapping
("/hasCashAccount")
public boolean hasCashAccount(){
  return family.hasCashAccount();
}


@GetMapping
("/addAccountID")
public boolean addAccountID(@RequestParam(name = "newAccountID") AccountID newAccountID){
  return family.addAccountID(newAccountID);
}


}