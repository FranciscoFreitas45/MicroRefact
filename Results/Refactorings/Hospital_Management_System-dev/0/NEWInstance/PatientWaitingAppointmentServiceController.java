import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class PatientWaitingAppointmentServiceController {

 private PatientWaitingAppointmentService patientwaitingappointmentservice;


@PutMapping
("/save")
public void save(@RequestParam(name = "patientWaitingAppointment") PatientWaitingAppointment patientWaitingAppointment){
patientwaitingappointmentservice.save(patientWaitingAppointment);
}


}