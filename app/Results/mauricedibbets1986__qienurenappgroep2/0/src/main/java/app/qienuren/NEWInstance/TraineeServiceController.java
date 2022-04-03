package app.qienuren.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TraineeServiceController {

 private TraineeService traineeservice;


@GetMapping
("/addTrainee")
public Trainee addTrainee(@RequestParam(name = "trainee") Trainee trainee){
  return traineeservice.addTrainee(trainee);
}


@GetMapping
("/getAllTrainees")
public Iterable<Trainee> getAllTrainees(){
  return traineeservice.getAllTrainees();
}


@GetMapping
("/traineeKoppelContactPersoon")
public Trainee traineeKoppelContactPersoon(@RequestParam(name = "traineeID") long traineeID,@RequestParam(name = "kcpID") long kcpID){
  return traineeservice.traineeKoppelContactPersoon(traineeID,kcpID);
}


@GetMapping
("/wijzigGegevens")
public Trainee wijzigGegevens(@RequestParam(name = "oorspronkelijkeId") long oorspronkelijkeId,@RequestParam(name = "id") long id){
  return traineeservice.wijzigGegevens(oorspronkelijkeId,id);
}


@GetMapping
("/getTraineesByKCPId")
public Iterable<Trainee> getTraineesByKCPId(@RequestParam(name = "kcpId") long kcpId){
  return traineeservice.getTraineesByKCPId(kcpId);
}


}