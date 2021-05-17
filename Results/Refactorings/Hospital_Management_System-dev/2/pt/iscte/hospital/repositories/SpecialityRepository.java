import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.hospital.entities.Speciality;
import java.util.List;
@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Long> {


public Speciality findByName(String name)


public List<Speciality> findAll()


public Speciality getSpeciality(Long Long)

public void setSpeciality(Long Long,Speciality speciality)

}