package pt.iscte.hospital.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.repositories.SpecialityRepository;
import pt.iscte.hospital.entities.Speciality;
@Service
public class SpecialityDoctorService {

@Autowired
 private SpecialityRepository specialityrepository;


public Speciality getSpeciality(Long specialityId){
return specialityrepository.getSpeciality(specialityId);
}


public void setSpeciality(Long specialityId,Speciality speciality){
specialityrepository.setSpeciality(specialityId,speciality);
}


}