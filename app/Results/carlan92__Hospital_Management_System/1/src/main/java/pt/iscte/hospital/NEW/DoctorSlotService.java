package pt.iscte.hospital.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.repositories.user.DoctorRepository;
import pt.iscte.hospital.entities.Doctor;
@Service
public class DoctorSlotService {

@Autowired
 private DoctorRepository doctorrepository;


public Doctor getDoctor(Long userId){
return doctorrepository.getDoctor(userId);
}


public void setDoctor(Long userId,Doctor doctor){
doctorrepository.setDoctor(userId,doctor);
}


}