import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class DoctorWaitingPatientRepositoryController {

 private DoctorWaitingPatientRepository doctorwaitingpatientrepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return doctorwaitingpatientrepository.save(Object);
}


}