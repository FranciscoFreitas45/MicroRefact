import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.iscte.hospital.entities.waiting.DoctorWaitingPatient;
import pt.iscte.hospital.repositories.waiting.DoctorWaitingPatientRepository;
import java.time.LocalDate;
import java.util.List;
@Service
public class DoctorWaitingPatientServiceImpl implements DoctorWaitingPatientService,pt.iscte.hospital.services.waiting.DoctorWaitingPatientService{

@Autowired
 private DoctorWaitingPatientRepository doctorWaitingPatientRepository;


public List<DoctorWaitingPatient> findAllByDate(LocalDate date){
    return doctorWaitingPatientRepository.findAllByDate(date);
}


}