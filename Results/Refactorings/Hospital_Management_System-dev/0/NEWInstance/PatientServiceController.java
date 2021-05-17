import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class PatientServiceController {

 private PatientService patientservice;


@GetMapping
("/findByUserId")
public Patient findByUserId(@RequestParam(name = "patientId") Long patientId){
  return patientservice.findByUserId(patientId);
}


}