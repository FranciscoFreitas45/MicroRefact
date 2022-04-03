package pt.iscte.hospital.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.entities.Appointment;
@RestController
@CrossOrigin
public class AppointmentSlotController {

@Autowired
 private AppointmentSlotService appointmentslotservice;


}