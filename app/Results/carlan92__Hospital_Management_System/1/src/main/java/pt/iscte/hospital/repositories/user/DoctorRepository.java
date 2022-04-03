package pt.iscte.hospital.repositories.user;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.hospital.entities.Doctor;
import pt.iscte.hospital.entities.Speciality;
import javax.print.Doc;
import java.util.List;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{


public List<Doctor> findAllByNameContainingIgnoreCase(String name)
;

public Doctor findByUserId(Long doctorId)
;

public List<Doctor> findAllBySpecialityOrderByNameAsc(Speciality speciality)
;

public List<Doctor> findAllByNameContainingIgnoreCaseAndSpeciality(String name,Speciality speciality)
;

public List<Doctor> findAll()
;

public List<Doctor> findAllBySpeciality(Speciality speciality)
;

public List<Doctor> getDoctors(Long specialityId);

public void setDoctors(Long specialityId,List<Doctor> doctors);

public Doctor getDoctor(Long userId);

public void setDoctor(Long userId,Doctor doctor);

public Doctor getDoctor(Long userId);

public void setDoctor(Long userId,Doctor doctor);

}