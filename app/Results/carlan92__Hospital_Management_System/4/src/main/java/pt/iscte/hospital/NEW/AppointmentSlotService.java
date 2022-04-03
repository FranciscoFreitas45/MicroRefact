package pt.iscte.hospital.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.repositories.AppointmentRepository;
import pt.iscte.hospital.entities.Appointment;
@Service
public class AppointmentSlotService {

@Autowired
 private AppointmentRepository appointmentrepository;


}