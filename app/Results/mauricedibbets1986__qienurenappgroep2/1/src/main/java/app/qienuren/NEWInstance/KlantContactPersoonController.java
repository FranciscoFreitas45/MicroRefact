package app.qienuren.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class KlantContactPersoonController {

 private KlantContactPersoonRepository klantcontactpersoonrepository;


@PutMapping
("/koppelTrainee/{id}")
public void koppelTrainee(@PathVariable(name = "id") long id,@RequestParam(name = "trainee") Trainee trainee){
 klantcontactpersoonrepository.koppelTrainee(id,trainee);
}


}