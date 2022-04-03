package app.qienuren.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.model.Trainee;
@RestController
@CrossOrigin
public class TraineeKlantContactPersoonController {

@Autowired
 private TraineeKlantContactPersoonService traineeklantcontactpersoonservice;


@PutMapping
("/KlantContactPersoon/{id}/Trainee/koppelTrainee")
public void koppelTrainee(@PathVariable(name="id") long id,@RequestBody Trainee trainee){
traineeklantcontactpersoonservice.koppelTrainee(id,trainee);
}


@GetMapping
("/KlantContactPersoon/{id}/Trainee/getTrainees")
public List<Trainee> getTrainees(@PathVariable(name="id") long id){
return traineeklantcontactpersoonservice.getTrainees(id);
}


@PutMapping
("/KlantContactPersoon/{id}/Trainee/setTrainees")
public void setTrainees(@PathVariable(name="id") long id,@RequestBody List<Trainee> trainees){
traineeklantcontactpersoonservice.setTrainees(id,trainees);
}


}