package app.qienuren.rest;
 import app.qienuren.controller.PersoonService;
import app.qienuren.controller.TijdelijkePersoonService;
import app.qienuren.model.Persoon;
import app.qienuren.model.TijdelijkeInterneMedewerker;
import app.qienuren.model.TijdelijkePersoon;
import app.qienuren.model.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
@RestController
@RequestMapping("/api/user")
public class UserEndpoint {

@Autowired
 private PersoonService persoonService;

@Autowired
 private TijdelijkePersoonService tijdelijkePersoonService;


@GetMapping("/test")
public String test(){
    return "het werkt";
}


@DeleteMapping("/tijdelijkepersoon/delete/{id}")
public void deleteTijdelijkePersoonById(long id){
    tijdelijkePersoonService.deleteTijdelijkePersoonById(id);
}


@GetMapping("/tijdelijkepersonen/all")
public Iterable<TijdelijkePersoon> getAlleTijdelijkePersonen(){
    return tijdelijkePersoonService.getallTijdelijkePersonen();
}


@GetMapping("/tijdelijkepersoon/{id}")
public TijdelijkePersoon getTijdelijkePersoonById(long id){
    return tijdelijkePersoonService.getById(id);
}


@PostMapping("/add")
public Persoon newTrainee(Persoon persoon){
    // persoon.setEmail(persoon.getUserName());
    return persoonService.addPersoon(persoon);
}


@GetMapping("/{id}")
public Persoon getPersoonById(long id){
    return persoonService.getById(id);
}


}