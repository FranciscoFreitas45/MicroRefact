package app.qienuren.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MedewerkerController {

 private MedewerkerRepository medewerkerrepository;


@PutMapping
("/verwijderFormulierUitTijdelijkeLijst/{id}")
public void verwijderFormulierUitTijdelijkeLijst(@PathVariable(name = "id") long id,@RequestParam(name = "tf") Formulier tf){
 medewerkerrepository.verwijderFormulierUitTijdelijkeLijst(id,tf);
}


}