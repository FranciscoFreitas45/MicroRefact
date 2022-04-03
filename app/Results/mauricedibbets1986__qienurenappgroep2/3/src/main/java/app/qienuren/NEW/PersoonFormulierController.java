package app.qienuren.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.model.Persoon;
@RestController
@CrossOrigin
public class PersoonFormulierController {

@Autowired
 private PersoonFormulierService persoonformulierservice;


@PutMapping
("/Formulier/{id}/Persoon/setMedewerker")
public void setMedewerker(@PathVariable(name="id") long idN8E9,@RequestBody Persoon medewerker){
persoonformulierservice.setMedewerker(idN8E9,medewerker);
}


@GetMapping
("/Formulier/{id}/Persoon/getMedewerker")
public Persoon getMedewerker(@PathVariable(name="id") long idN8E9){
return persoonformulierservice.getMedewerker(idN8E9);
}


}