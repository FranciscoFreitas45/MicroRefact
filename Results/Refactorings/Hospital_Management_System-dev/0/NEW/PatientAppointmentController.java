import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class PatientAppointmentController {

 private PatientAppointmentService patientappointmentservice;


@PutMapping
("/Appointment/{id}/Patient/setPatient")
public void setPatient(@PathVariable(name="id") Long Long,@RequestBody Patient patient){
patientappointmentservice.setPatient(Long,patient);
}


@GetMapping
("/Appointment/{id}/Patient/getPatient")
public Patient getPatient(@PathVariable(name="id") Long Long){
patientappointmentservice.getPatient(Long);
}


}