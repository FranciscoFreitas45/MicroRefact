import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class DoctorSlotService {

 private DoctorRepository doctorrepository;


public Doctor getDoctor(Long Long){
doctorrepository.getDoctor(Long);
}


public void setDoctor(Long Long,Doctor doctor){
doctorrepository.setDoctor(Long,doctor);
}


}