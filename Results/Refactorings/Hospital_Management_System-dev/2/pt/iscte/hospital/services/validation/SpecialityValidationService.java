import org.springframework.ui.ModelMap;
import pt.iscte.hospital.entities.Speciality;
public interface SpecialityValidationService {


public SpecialityValidationService validName()


public boolean isValid()


public SpecialityValidationService validLength()


public SpecialityValidationService clear()


public SpecialityValidationService validPrice()


public SpecialityValidationService setSpeciality(Speciality speciality)


public ModelMap getErrorModelMap()


}