import org.springframework.data.domain.Sort;
import pt.iscte.hospital.entities.Speciality;
import java.util.List;
public interface SpecialityService {


public void addSpeciality(Speciality speciality)


public Speciality findByName(String name)


public List<Speciality> findAll()


}