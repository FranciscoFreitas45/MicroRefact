import org.springframework.data.domain.Sort;
import pt.iscte.hospital.entities.Appointment;
import pt.iscte.hospital.entities.Doctor;
import pt.iscte.hospital.entities.Speciality;
import java.util.List;
public interface DoctorService {


public void desmarcarConsultaByDoctor(Appointment appointment)


public List<Doctor> findAllByFirstAndLastNameAndSpeciality(String name,String specialityName)


public void marcarFalta(Appointment appointment)


public List<Doctor> findAllByNameContainingIgnoreCase(String name)


public void removerFalta(Appointment appointment)


public List<Doctor> findAllBySpecialityOrderByNameAsc(Speciality speciality)


public List<Doctor> findAll()


public List<Doctor> findAllBySpeciality(String specialityName)


public void startAppointment(Appointment appointment)


public List<Doctor> findAllByFirstAndLastName(String name)


public void chamarUtente(Appointment appointment)


public void endAppointment(Appointment appointment)


public Doctor findByUserId(Long doctorId)


public List<Doctor> findAllByNameContainingIgnoreCaseAndSpeciality(String name,String specialityName)


}