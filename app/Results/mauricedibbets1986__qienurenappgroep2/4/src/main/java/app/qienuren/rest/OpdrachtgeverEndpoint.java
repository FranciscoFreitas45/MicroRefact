package app.qienuren.rest;
 import app.qienuren.controller.FormulierService;
import app.qienuren.controller.KlantContactPersoonService;
import app.qienuren.controller.PersoonService;
import app.qienuren.controller.TraineeService;
import app.qienuren.model.Formulier;
import app.qienuren.model.KlantContactPersoon;
import app.qienuren.model.Persoon;
import app.qienuren.model.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import app.qienuren.Interface.TraineeService;
import app.qienuren.Interface.KlantContactPersoonService;
@RestController
@RequestMapping("api/opdrachtgever")
public class OpdrachtgeverEndpoint {

@Autowired
 private FormulierService formulierService;

@Autowired
 private TraineeService traineeService;

@Autowired
 private KlantContactPersoonService klantContactPersoonService;


@GetMapping("/trainees/{kcpid}")
public Iterable<Trainee> getAllTrainees(long id){
    return traineeService.getTraineesByKCPId(id);
}


@GetMapping("/formulieren/all")
public Iterable<Formulier> alleFormulieren(){
    return formulierService.getAlleFormulierenVoorOpdrachtGever();
}


@GetMapping("/{id}")
public KlantContactPersoon getKCPById(long id){
    return klantContactPersoonService.getKCPById(id);
}


@PutMapping("/wachtwoordwijzigen/{id}")
public void kcpWachtwoordWijzigen(KlantContactPersoon kcp,long id){
    klantContactPersoonService.kcpWachtwoordWijzigen(id, kcp);
}


@PutMapping("/update/statusfout/{formulierid}/{medewerkerid}")
public Formulier updateFormulierStatusFout(long formulierid,long medewerkerid){
    return formulierService.OpdrachtgeverStatusFout(formulierid, medewerkerid);
}


@PutMapping("/update/statusgoed/{formulierid}/{medewerkerid}")
public Formulier updateFormulierStatusGoed(long formulierid,long medewerkerid){
    return formulierService.OpdrachtgeverStatusGoed(formulierid, medewerkerid);
}


}