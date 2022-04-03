package pt.iscte.hospital.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.entities.Patient;
@RestController
@CrossOrigin
public class PatientAppointmentController {

@Autowired
 private PatientAppointmentService patientappointmentservice;


@PutMapping
("/Appointment/{id}/Patient/setPatient")
public void setPatient(@PathVariable(name="id") Long userId,@RequestBody Patient patient){
patientappointmentservice.setPatient(userId,patient);
}


@GetMapping
("/Appointment/{id}/Patient/getPatient")
public Patient getPatient(@PathVariable(name="id") Long userId){
return patientappointmentservice.getPatient(userId);
}


}