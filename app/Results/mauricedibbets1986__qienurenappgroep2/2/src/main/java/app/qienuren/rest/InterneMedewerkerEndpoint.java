package app.qienuren.rest;
 import app.qienuren.controller.FormulierService;
import app.qienuren.controller.InterneMedewerkerService;
import app.qienuren.controller.TijdelijkeInterneMedewerkerService;
import app.qienuren.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import app.qienuren.Interface.FormulierService;
@RestController
@RequestMapping("/api/internemedewerker")
public class InterneMedewerkerEndpoint {

@Autowired
 private InterneMedewerkerService interneMedewerkerService;

@Autowired
 private FormulierService formulierService;

@Autowired
 private TijdelijkeInterneMedewerkerService tijdelijkeInterneMedewerkerService;


@PostMapping("/nieuwegegevens/{id}")
public TijdelijkeInterneMedewerker addTijdelijkeMedewerker(long id,TijdelijkeInterneMedewerker medewerker){
    return tijdelijkeInterneMedewerkerService.addTijdelijkeMedewerker(id, medewerker);
}


@GetMapping("/tijdelijkemedewerker/oorspronkelijkemedewerkerid/{oorspronkelijkemedewerkerid}")
public TijdelijkeInterneMedewerker getTijdelijkeMedewerkerByOorspronkelijkeId(long id){
    return tijdelijkeInterneMedewerkerService.getTijdelijkeMedewerkerByOorspronkelijkeId(id);
}


@PutMapping("/wachtwoordwijzigen/{id}")
public void internemedewerkerWachtwoordWijzigen(InterneMedewerker interneMedewerker,long id){
    interneMedewerkerService.internemedewerkerWachtwoordWijzigen(id, interneMedewerker);
}


@GetMapping("/tijdelijkemedewerker/tijdelijkemedewerkerid/{tijdelijkemedewerkerid}")
public TijdelijkeInterneMedewerker getTijdelijkeMedewerkerById(long id){
    return tijdelijkeInterneMedewerkerService.getTijdelijkeMedewerkerById(id);
}


@PutMapping("/formulier/update/{formulierid}")
public Formulier updateFormulier(Formulier tf){
    return formulierService.updateFormulier(tf);
}


@GetMapping("/formulier/{medewerkerid}/{formulierId}")
public Formulier getFormulier(long medewerkerid,long formulierId){
    InterneMedewerker im = interneMedewerkerService.getInterneMedewerkerById(medewerkerid);
    Iterable<Formulier> formulieren = im.getTijdelijkeFormulieren();
    for (Formulier f : formulieren) {
        if (f.getId() == formulierId) {
            return f;
        }
    }
    return null;
}


@GetMapping("/{id}")
public InterneMedewerker getInterneMedewerkerById(long id){
    return interneMedewerkerService.getInterneMedewerkerById(id);
}


}