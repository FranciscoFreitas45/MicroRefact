package pt.iscte.hospital.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.entities.Doctor;
@RestController
@CrossOrigin
public class DoctorPatientWaitingAppointmentController {

@Autowired
 private DoctorPatientWaitingAppointmentService doctorpatientwaitingappointmentservice;


@GetMapping
("/PatientWaitingAppointment/{id}/Doctor/getDoctor")
public Doctor getDoctor(@PathVariable(name="id") Long userId){
return doctorpatientwaitingappointmentservice.getDoctor(userId);
}


@PutMapping
("/PatientWaitingAppointment/{id}/Doctor/setDoctor")
public void setDoctor(@PathVariable(name="id") Long userId,@RequestBody Doctor doctor){
doctorpatientwaitingappointmentservice.setDoctor(userId,doctor);
}


}