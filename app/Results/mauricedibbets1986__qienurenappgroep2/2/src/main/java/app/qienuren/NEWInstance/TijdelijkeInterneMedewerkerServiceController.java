package app.qienuren.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TijdelijkeInterneMedewerkerServiceController {

 private TijdelijkeInterneMedewerkerService tijdelijkeinternemedewerkerservice;


@GetMapping
("/getallTijdelijkeMedewerkers")
public Iterable<TijdelijkeInterneMedewerker> getallTijdelijkeMedewerkers(){
  return tijdelijkeinternemedewerkerservice.getallTijdelijkeMedewerkers();
}


}