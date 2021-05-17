import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class AppointmentServiceController {

 private AppointmentService appointmentservice;


@GetMapping
("/findByAppointmentId")
public Appointment findByAppointmentId(@RequestParam(name = "appointmentId") Long appointmentId){
  return appointmentservice.findByAppointmentId(appointmentId);
}


@PutMapping
("/saveAppointment")
public void saveAppointment(@RequestParam(name = "appointment") Appointment appointment){
appointmentservice.saveAppointment(appointment);
}


}