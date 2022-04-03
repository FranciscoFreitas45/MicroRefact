package pt.iscte.hospital.services.waiting;
 import pt.iscte.hospital.entities.waiting.DoctorWaitingPatient;
import java.time.LocalDate;
import java.util.List;
public interface DoctorWaitingPatientService {


public List<DoctorWaitingPatient> findAllByDate(LocalDate date)
;

}