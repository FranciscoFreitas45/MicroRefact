package app.qienuren.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PersoonServiceController {

 private PersoonService persoonservice;


@GetMapping
("/getAllMedewerkers")
public Iterable<Persoon> getAllMedewerkers(){
  return persoonservice.getAllMedewerkers();
}


@GetMapping
("/wijzigGegevens")
public Persoon wijzigGegevens(@RequestParam(name = "oorspronkelijkeId") long oorspronkelijkeId,@RequestParam(name = "id") long id){
  return persoonservice.wijzigGegevens(oorspronkelijkeId,id);
}


@GetMapping
("/getById")
public Persoon getById(@RequestParam(name = "id") long id){
  return persoonservice.getById(id);
}


}