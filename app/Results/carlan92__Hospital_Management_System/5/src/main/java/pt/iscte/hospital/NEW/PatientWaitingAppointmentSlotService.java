package pt.iscte.hospital.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.repositories.waiting.PatientWaitingAppointmentRepository;
import pt.iscte.hospital.entities.waiting.PatientWaitingAppointment;
@Service
public class PatientWaitingAppointmentSlotService {

@Autowired
 private PatientWaitingAppointmentRepository patientwaitingappointmentrepository;


}