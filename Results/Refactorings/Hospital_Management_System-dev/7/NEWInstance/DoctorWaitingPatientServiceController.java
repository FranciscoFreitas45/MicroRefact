import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class DoctorWaitingPatientServiceController {

 private DoctorWaitingPatientService doctorwaitingpatientservice;


@GetMapping
("/findAllByDate")
public List<DoctorWaitingPatient> findAllByDate(@RequestParam(name = "date") LocalDate date){
  return doctorwaitingpatientservice.findAllByDate(date);
}


}