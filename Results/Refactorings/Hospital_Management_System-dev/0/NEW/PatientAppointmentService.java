import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class PatientAppointmentService {

 private PatientRepository patientrepository;


public void setPatient(Long Long,Patient patient){
patientrepository.setPatient(Long,patient);
}


public Patient getPatient(Long Long){
patientrepository.getPatient(Long);
}


}