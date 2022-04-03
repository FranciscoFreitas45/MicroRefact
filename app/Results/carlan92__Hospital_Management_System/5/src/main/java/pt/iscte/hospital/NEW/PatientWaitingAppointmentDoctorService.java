package pt.iscte.hospital.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.repositories.waiting.PatientWaitingAppointmentRepository;
import pt.iscte.hospital.entities.waiting.PatientWaitingAppointment;
@Service
public class PatientWaitingAppointmentDoctorService {

@Autowired
 private PatientWaitingAppointmentRepository patientwaitingappointmentrepository;


}