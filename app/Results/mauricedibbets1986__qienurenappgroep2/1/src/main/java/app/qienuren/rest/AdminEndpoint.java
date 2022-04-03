package app.qienuren.rest;
 import app.qienuren.controller;
import app.qienuren.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import app.qienuren.Interface.TraineeService;
import app.qienuren.Interface.InterneMedewerkerService;
import app.qienuren.Interface.PersoonService;
import app.qienuren.Interface.FormulierService;
import app.qienuren.Interface.TijdelijkeTraineeService;
import app.qienuren.Interface.TijdelijkeInterneMedewerkerService;
import app.qienuren.Interface.InterneMedewerkerService;
@RestController
@RequestMapping("/api/admin")
public class AdminEndpoint {

@Autowired
 private AdminService adminService;

@Autowired
 private TraineeService traineeService;

@Autowired
 private InterneMedewerkerService interneMedewerkerService;

@Autowired
 private BedrijfService bedrijfService;

@Autowired
 private KlantContactPersoonService klantContactPersoonService;

@Autowired
 private PersoonService persoonService;

@Autowired
 private FormulierService formulierService;

@Autowired
 private TijdelijkeTraineeService tijdelijkeTraineeService;

@Autowired
 private TijdelijkeInterneMedewerkerService tijdelijkeInterneMedewerkerService;

@Autowired
 private MedewerkerService medewerkerService;


@PutMapping("/goedkeurengegevens/{oorspronkelijkeId}/{id}")
public Trainee goedkeurenGegevensWijziging(long oorspronkelijkeId,long id){
    return traineeService.wijzigGegevens(oorspronkelijkeId, id);
}


@PutMapping("/klantcontactpersoon/koppelbedrijf/{id}/{bedrijfid}")
public void klantContactPersoonToevoegenBedrijf(long kcpID,long bedrijfID){
    bedrijfService.klantContactPersoonToevoegenBedrijf(kcpID, bedrijfID);
}


@GetMapping("/formulieren/ingezonden")
public Iterable<Formulier> alleFormulieren(){
    return formulierService.getAlleFormulierenVoorAdmin();
}


@PutMapping("/goedkeurengegevens/internemedewerker/{oorspronkelijkeId}/{id}")
public InterneMedewerker goedkeurenGegevensWijzigingMedewerker(long oorspronkelijkeId,long id){
    return interneMedewerkerService.wijzigGegevens(oorspronkelijkeId, id);
}


@PutMapping("/trainee/koppelContactPersoon/{id}/{bedrijfid}")
public void traineeKoppelKlantContactPersoon(long traineeID,long kcpID){
    traineeService.traineeKoppelContactPersoon(traineeID, kcpID);
}


@PutMapping("/update/statusfout/{formulierid}/{medewerkerid}")
public Formulier updateFormulierStatusFout(long formulierid,long medewerkerid){
    return formulierService.AdminStatusFout(formulierid, medewerkerid);
}


@PutMapping("/update/statusgoed/{formulierid}/{medewerkerid}")
public Formulier updateFormulierStatusGoed(long formulierid,long medewerkerid){
    return formulierService.AdminStatusGoed(formulierid, medewerkerid);
}


@GetMapping("/medewerker/{id}")
public Medewerker getMedewerkerById(long medewerkerId){
    System.out.println("ophalen medewerkert in endpoint");
    return medewerkerService.getMedewerkerById(medewerkerId);
}


@PostMapping("/bedrijf/nieuw")
public Bedrijf newBedrijf(Bedrijf bedrijf){
    return bedrijfService.addBedrijf(bedrijf);
}


@GetMapping("internemedewerker/all")
public Iterable<InterneMedewerker> getAllInterneMederwerkers(){
    return interneMedewerkerService.getAllInterneMedewerkers();
}


@GetMapping("klantcontactpersoon/all")
public Iterable<KlantContactPersoon> getAllKlantContactPersoon(){
    return klantContactPersoonService.getAllKlantContactPersoon();
}


@GetMapping("bedrijf/all")
public Iterable<Bedrijf> getAllBedrijf(){
    return bedrijfService.getAllBedrijf();
}


@GetMapping("/tijdelijkeTrainee/all")
public Iterable<TijdelijkeTrainee> getAlleTijdelijkeTrainee(){
    return tijdelijkeTraineeService.getAllTijdelijkeTrainee();
}


@GetMapping("/tijdelijkeMedewerker/all")
public Iterable<TijdelijkeInterneMedewerker> getAlleTijdelijkeMedewerkers(){
    return tijdelijkeInterneMedewerkerService.getallTijdelijkeMedewerkers();
}


@PostMapping("/internemedewerker/nieuw")
public InterneMedewerker newInterneMedewerker(InterneMedewerker interneMedewerker){
    return interneMedewerkerService.addInterneMederwerker(interneMedewerker);
}


@PostMapping("klantcontactpersoon/nieuw")
public KlantContactPersoon newKlantContactPersoon(KlantContactPersoon klantContactPersoon,long bedrijfsId){
    Bedrijf bedrijf = bedrijfService.getBedrijfbyID(bedrijfsId);
    klantContactPersoon.setBedrijf(bedrijf);
    return klantContactPersoonService.addKlantContactPersoon(klantContactPersoon);
}


@GetMapping("/medewerker/all")
public Iterable<Persoon> getAllMedewerkers(){
    return persoonService.getAllMedewerkers();
}


@PostMapping("/trainee/nieuw")
public Trainee newTrainee(Trainee trainee){
    return traineeService.addTrainee(trainee);
}


@GetMapping("/trainee/all")
public Iterable<Trainee> alleTrainees(){
    return traineeService.getAllTrainees();
}


@PutMapping("/goedkeurengegevens/persoon/{oorspronkelijkeId}/{id}")
public Persoon goedkeurenGegevensWijzigingPersoon(long oorspronkelijkeId,long id){
    return persoonService.wijzigGegevens(oorspronkelijkeId, id);
}


}