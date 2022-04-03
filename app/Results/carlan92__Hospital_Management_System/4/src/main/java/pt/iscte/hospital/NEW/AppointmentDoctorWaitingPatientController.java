package pt.iscte.hospital.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.entities.Appointment;
@RestController
@CrossOrigin
public class AppointmentDoctorWaitingPatientController {

@Autowired
 private AppointmentDoctorWaitingPatientService appointmentdoctorwaitingpatientservice;


@GetMapping
("/DoctorWaitingPatient/{id}/Appointment/getAppointment")
public Appointment getAppointment(@PathVariable(name="id") Long appointmentId){
return appointmentdoctorwaitingpatientservice.getAppointment(appointmentId);
}


}