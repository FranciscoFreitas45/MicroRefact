package pt.iscte.hospital.repositories.waiting;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.hospital.entities.waiting.DoctorWaitingPatient;
import java.time.LocalDate;
import java.util.List;
@Repository
public interface DoctorWaitingPatientRepository extends JpaRepository<DoctorWaitingPatient, Long>{


public List<DoctorWaitingPatient> findAllByDate(LocalDate date)
;

public DoctorWaitingPatient getDoctorWaitingPatient(Long doctorWaitingPatientId);

public void setDoctorWaitingPatient(Long doctorWaitingPatientId,DoctorWaitingPatient doctorWaitingPatient);

}