package pt.iscte.hospital.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.repositories.AppointmentRepository;
import pt.iscte.hospital.entities.Appointment;
@Service
public class AppointmentInvoiceService {

@Autowired
 private AppointmentRepository appointmentrepository;


public Appointment getAppointment(Long appointmentId){
return appointmentrepository.getAppointment(appointmentId);
}


}