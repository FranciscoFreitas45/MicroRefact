package pt.iscte.hospital.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.entities.waiting.PatientWaitingAppointment;
@RestController
@CrossOrigin
public class PatientWaitingAppointmentSlotController {

@Autowired
 private PatientWaitingAppointmentSlotService patientwaitingappointmentslotservice;


}