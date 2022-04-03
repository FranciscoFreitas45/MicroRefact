package app.qienuren.rest;
 import app.qienuren.controller.TijdelijkeTraineeService;
import app.qienuren.controller.TraineeService;
import app.qienuren.model.TijdelijkeTrainee;
import app.qienuren.controller.FormulierService;
import app.qienuren.model.Formulier;
import app.qienuren.model.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import app.qienuren.Interface.FormulierService;
@RestController
@RequestMapping("/api/trainee")
public class TraineeEndpoint {

@Autowired
 private FormulierService formulierService;

@Autowired
 private TraineeService traineeService;

@Autowired
 private TijdelijkeTraineeService tijdelijkeTraineeService;


@PutMapping("/wachtwoordwijzigen/{id}")
public void traineeWachtwoordWijzigen(Trainee trainee,long traineeID){
    traineeService.traineeWachtwoordWijzigen(traineeID, trainee);
}


@GetMapping("/tijdelijkeformulieren/{id}")
public Iterable<Formulier> getTijdelijkeFormulierenByTraineeId(long id){
    Trainee t = traineeService.getTraineeById(id);
    return t.getTijdelijkeFormulieren();
}


@PostMapping("/nieuwegegevens/{id}")
public TijdelijkeTrainee addTijdelijkeTrainee(long traineeID,TijdelijkeTrainee tijdtrainee){
    return tijdelijkeTraineeService.addTijdelijkeTrainee(traineeID, tijdtrainee);
}


@PutMapping("/trainee/koppelFormulier/{id}/{formulierid}")
public void traineeKoppelFormulier(long traineeID,long formulierid){
    traineeService.traineeKoppelformulier(traineeID, formulierid);
}


@PutMapping("/formulier/update/{formulierid}")
public Formulier updateFormulier(Formulier tf){
    return formulierService.updateFormulier(tf);
}


@GetMapping("/formulier/{traineeId}/{formulierId}")
public Formulier getFormulier(long traineeId,long formulierId){
    Trainee t = traineeService.getTraineeById(traineeId);
    Iterable<Formulier> formulieren = t.getTijdelijkeFormulieren();
    for (Formulier f : formulieren) {
        if (f.getId() == formulierId) {
            return f;
        }
    }
    return null;
}


@GetMapping("/tijdelijketrainee/tijdelijketraineeid/{tijdelijketraineeid}")
public TijdelijkeTrainee getTijdelijkeTraineeById(long tijdelijkeTraineeId){
    return tijdelijkeTraineeService.getTijdelijkeTraineeById(tijdelijkeTraineeId);
}


@GetMapping("/tijdelijketrainee/oorspronkelijketraineeid/{oorspronkelijketraineeid}")
public TijdelijkeTrainee getTijdelijkeTraineeByOorspronkelijkeId(long oorspronkelijkeId){
    return tijdelijkeTraineeService.getTijdelijkeTraineByOorspronkelijkeId(oorspronkelijkeId);
}


@GetMapping("/{id}")
public Trainee getTraineeById(long id){
    return traineeService.getTraineeById(id);
}


}