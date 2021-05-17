import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.hospital.entities.Patient;
import java.util.List;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {


public Patient findByUsername(String username)


public List<Patient> findAllByNameContainingIgnoreCase(String name)


public Patient findByUserId(Long patientId)


public Patient findByName(String name)


public Patient findByEmail(String email)


public void setPatient(Long Long,Patient patient)

public Patient getPatient(Long Long)

}