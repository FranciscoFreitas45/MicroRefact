package pt.iscte.hospital.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.repositories.user.PatientRepository;
import pt.iscte.hospital.entities.Patient;
@Service
public class PatientAppointmentService {

@Autowired
 private PatientRepository patientrepository;


public void setPatient(Long userId,Patient patient){
patientrepository.setPatient(userId,patient);
}


public Patient getPatient(Long userId){
return patientrepository.getPatient(userId);
}


}