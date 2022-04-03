package app.qienuren.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PersoonRepositoryController {

 private PersoonRepository persoonrepository;


@GetMapping
("/findByEmail")
public Persoon findByEmail(@RequestParam(name = "email") String email){
  return persoonrepository.findByEmail(email);
}


}