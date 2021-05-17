import org.springframework.data.domain.Sort;
import pt.iscte.hospital.entities.Patient;
import java.util.List;
public interface PatientService {


public Patient findByUsername(String username)


public List<Patient> findAllByNameContainingIgnoreCase(String name)


public Patient findByUserId(Long patientId)


public List<Patient> findAll(Sort sort)


public List<Patient> findAllByFirstAndLastName(String name)


}