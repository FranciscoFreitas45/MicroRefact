package pt.iscte.hospital.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.repositories.user.DoctorRepository;
import pt.iscte.hospital.entities.Doctor;
@Service
public class DoctorSpecialityService {

@Autowired
 private DoctorRepository doctorrepository;


public List<Doctor> getDoctors(Long specialityId){
return doctorrepository.getDoctors(specialityId);
}


public void setDoctors(Long specialityId,List<Doctor> doctors){
doctorrepository.setDoctors(specialityId,doctors);
}


}