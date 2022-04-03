package switchtwentytwenty.project.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ILedgerRepositoryController {

 private ILedgerRepository iledgerrepository;


@PutMapping
("/save")
public void save(@RequestParam(name = "entity") Ledger entity){
iledgerrepository.save(entity);
}


@GetMapping
("/findByID")
public Ledger findByID(@RequestParam(name = "id") LedgerID id){
  return iledgerrepository.findByID(id);
}


}