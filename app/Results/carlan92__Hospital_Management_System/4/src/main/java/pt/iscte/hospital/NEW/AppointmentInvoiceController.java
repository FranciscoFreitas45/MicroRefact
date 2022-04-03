package pt.iscte.hospital.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.entities.Appointment;
@RestController
@CrossOrigin
public class AppointmentInvoiceController {

@Autowired
 private AppointmentInvoiceService appointmentinvoiceservice;


@GetMapping
("/Invoice/{id}/Appointment/getAppointment")
public Appointment getAppointment(@PathVariable(name="id") Long appointmentId){
return appointmentinvoiceservice.getAppointment(appointmentId);
}


}