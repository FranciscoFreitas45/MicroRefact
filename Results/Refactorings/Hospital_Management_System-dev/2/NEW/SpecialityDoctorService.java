import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class SpecialityDoctorService {

 private SpecialityRepository specialityrepository;


public Speciality getSpeciality(Long Long){
specialityrepository.getSpeciality(Long);
}


public void setSpeciality(Long Long,Speciality speciality){
specialityrepository.setSpeciality(Long,speciality);
}


}