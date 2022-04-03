package app.qienuren.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TijdelijkeTraineeServiceController {

 private TijdelijkeTraineeService tijdelijketraineeservice;


@GetMapping
("/getAllTijdelijkeTrainee")
public Iterable<TijdelijkeTrainee> getAllTijdelijkeTrainee(){
  return tijdelijketraineeservice.getAllTijdelijkeTrainee();
}


}