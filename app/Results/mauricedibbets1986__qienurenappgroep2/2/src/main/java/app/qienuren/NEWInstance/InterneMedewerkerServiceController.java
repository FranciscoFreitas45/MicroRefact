package app.qienuren.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class InterneMedewerkerServiceController {

 private InterneMedewerkerService internemedewerkerservice;


@GetMapping
("/addInterneMederwerker")
public InterneMedewerker addInterneMederwerker(@RequestParam(name = "interneMedewerker") InterneMedewerker interneMedewerker){
  return internemedewerkerservice.addInterneMederwerker(interneMedewerker);
}


@GetMapping
("/getAllInterneMedewerkers")
public Iterable<InterneMedewerker> getAllInterneMedewerkers(){
  return internemedewerkerservice.getAllInterneMedewerkers();
}


@GetMapping
("/wijzigGegevens")
public InterneMedewerker wijzigGegevens(@RequestParam(name = "oorspronkelijkeId") long oorspronkelijkeId,@RequestParam(name = "id") long id){
  return internemedewerkerservice.wijzigGegevens(oorspronkelijkeId,id);
}


}