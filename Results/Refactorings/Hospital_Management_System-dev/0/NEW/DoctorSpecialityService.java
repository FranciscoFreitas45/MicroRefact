import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class DoctorSpecialityService {

 private DoctorRepository doctorrepository;


public List<Doctor> getDoctors(Long specialityId){
doctorrepository.getDoctors(specialityId);
}


public void setDoctors(Long specialityId,List<Doctor> doctors){
doctorrepository.setDoctors(specialityId,doctors);
}


}